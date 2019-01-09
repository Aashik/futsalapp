package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.BillDao;
import com.futsalmanagement.futsalapp.dao.DiscountDetailDao;
import com.futsalmanagement.futsalapp.dao.DiscountMasterDao;
import com.futsalmanagement.futsalapp.entity.*;
import com.futsalmanagement.futsalapp.model.SalesReport;
import com.futsalmanagement.futsalapp.model.Status;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillDao billDao;
    @Autowired
    private GroundService groundService;
    @Autowired
    private GameService gameService;
    @Autowired
    private DiscountMasterDao discountMasterDao;
    @Autowired
    private DiscountDetailDao discountDetailDao;
    @Autowired
    private ExpenseService expenseService;

//    @Override
//    public Bill insert(Bill bill) {
//       Bill insertedbill = billDao.save(bill);
//       return insertedbill;
//    }


//    @Override
//    public Map<String,BigDecimal> calculateTotalPrice(BillRequest billRequest) {
//        float unitPrice = 0;
//        int futsal_id = billRequest.getFutsal_id();
//        int ground_id = billRequest.getGround_id();
//        Map pricemap = new HashMap();
//        unitPrice = groundService.getGroundById(futsal_id,ground_id).getUnit_hour_price().floatValue();
//
//        if (discountService.isDiscountAvailable(futsal_id,ground_id, billRequest.getPlay_start_time())){
//            Discount discount = discountService.getDiscountById(futsal_id, ground_id);
//            //in percentage
//            int discountmargin = discount.getDiscount_margin();
//
//            float discountmarginvalue = (float)discountmargin/100;
//            System.out.println("discoutnmargin value " + discountmarginvalue);
//            float todiscountprice = discountmarginvalue * unitPrice;
//            System.out.println("to duscount price  " + todiscountprice);
//            unitPrice = unitPrice - todiscountprice;
//            System.out.println("the unit price after discount  " + unitPrice );
//        }
//
//        float playcost = (float)billRequest.getPlay_duration() * unitPrice ;
//        pricemap.put("play_cost" , new BigDecimal(playcost));
//
//        double extraexpense = 0.0 ;
//
//        for (Expense expense : billRequest.getExpenseList()){
//         //   extraexpense = extraexpense +  (expense.getUnit_price() * expense.getQuantity());
//        }
//
//        double totalcost = playcost + extraexpense;
//        pricemap.put("additional_cost", new BigDecimal(extraexpense));
//        return pricemap;
//
//    }


    @Override
    public Bill generateBill(int game_id) {
        Game game = gameService.findGameById(game_id);
        if (game != null){
            Bill bill = new Bill();
            bill.setCustomer_name(game.getCustomer().getFull_name());
            bill.setGame(game);
            BigDecimal playAmount = calculatePlayCost(game);
            BigDecimal additionalExpense = calculateExtraExpense(game);
            bill.setPlay_amount(playAmount);
            bill.setAddition_expense_amount(additionalExpense);
            int discountMargin = getDiscountMargin(game);
            System.out.println("discount margin " + discountMargin);
            if (discountMargin != 0) {
                bill.setDiscount_margin(discountMargin);
                float discountMarginvalue = (float) discountMargin / 100;
                BigDecimal toDiscountPrice = playAmount.multiply(new BigDecimal(discountMarginvalue));
                bill.setDiscountable_amount(toDiscountPrice);
                BigDecimal afterDiscountPrice = playAmount.subtract(toDiscountPrice);
                BigDecimal totalPayableAmount = afterDiscountPrice.add(additionalExpense);
                bill.setTotal_Amount(totalPayableAmount);
            } else {
                bill.setDiscount_margin(0);
                bill.setDiscountable_amount(null);
                BigDecimal totalPayableAmount = playAmount.add(additionalExpense);
                bill.setTotal_Amount(totalPayableAmount);
            }
            Bill savedBill = billDao.save(bill);
            return savedBill;
        }
        return null;
    }

    private BigDecimal calculatePlayCost(Game game){
        Ground playedGround = game.getPlayGround();
        BigDecimal unit_cost_price = playedGround.getUnit_hour_price();
        double play_duration = game.getPlay_duration();
        BigDecimal tmp = new BigDecimal(play_duration);
        BigDecimal product = unit_cost_price.multiply(tmp);
        return product;
    }

    private BigDecimal calculateExtraExpense(Game game){
        List<Expense> expenseList = expenseService.getAllExpensesByGame(game.getGame_id());
        BigDecimal totalSum = new BigDecimal(0);

        for (Expense expense : expenseList){
            totalSum = totalSum.add(expense.getAmount());
        }
        return totalSum;
    }

    private int getDiscountMargin(Game game){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int futsal_id = game.getPlayFutsal().getFutsal_id();
        int ground_id = game.getPlayGround().getGround_id();
        String playStarttime = game.getPlay_start_time();
        String playDate = game.getPlay_date();

        DiscountMaster founddiscountMaster = discountMasterDao.findAll().stream()
                .filter(discountMaster -> discountMaster.getFutsal().getFutsal_id() == futsal_id &&
                         discountMaster.getGround().getGround_id() == ground_id
                        && isRequestDateIsBetweenDiscountPeriod(playDate, discountMaster.getDate_from(),discountMaster.getDate_to())
                        && discountMaster.getStatus().equals(Status.ACTIVE.toString())).findAny().orElse(null);
        System.out.println("found discount master -->> " + founddiscountMaster);

        if (founddiscountMaster != null){
            List<DiscountDetail> discountDetailList = discountDetailDao.findAll().stream()
                    .filter(discountDetail -> discountDetail.getDiscountMaster().getDiscount_master_id() == founddiscountMaster.getDiscount_master_id())
                    .collect(Collectors.toList());

            for (DiscountDetail discountDetail : discountDetailList){
                String discountTimeFrom = discountDetail.getTime_from();
                String discountTimeTo = discountDetail.getTime_to();
                try {
                    Date gameStarttime = sdf.parse(playDate + " " + playStarttime + ":00");
                    Date discountStart = sdf.parse(playDate + " " + discountTimeFrom + ":00");
                    Date discountEnd = sdf.parse(playDate + " " + discountTimeTo+ ":00");
                    DateTime dt_gameStarttime = new DateTime(gameStarttime);
                    DateTime dt_discountStart = new DateTime(discountStart);
                    DateTime dt_discountEnd = new DateTime(discountEnd);
                    System.out.println("start time is after discount start check  -->> " + dt_gameStarttime.isAfter(dt_discountStart));
                    System.out.println("start tiem is equals discount start check -->> " + dt_gameStarttime.equals(dt_discountStart));
                    System.out.println("start time is before end time check -->> " + dt_gameStarttime.isBefore(dt_discountEnd));
                    boolean checkdiscountForTime = ((dt_gameStarttime.isAfter(dt_discountStart) || dt_gameStarttime.equals(dt_discountStart)) && dt_gameStarttime.isBefore(dt_discountEnd));
                    if (checkdiscountForTime) return discountDetail.getMargin();
                }catch (ParseException e){
                    e.printStackTrace();
                    return 0;
                }
            }
        }
        return 0;
    }

    private boolean isRequestDateIsBetweenDiscountPeriod(String request_date, String date_from, String date_to){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    try{
        Date requestDate = sdf.parse(request_date +" " + "00:00:00");
        Date dateFrom = sdf.parse(date_from +" "+ "00:00:00");
        Date dateTo = sdf.parse(date_to +" "+ "00:00:00");
        DateTime dt_requestDate = new DateTime(requestDate);
        DateTime dt_dateFrom = new DateTime(dateFrom);
        DateTime dt_dateTo = new DateTime(dateTo);
        System.out.println("request date " + request_date);
        System.out.println("date from " + date_from);
        System.out.println("date to " + date_to);
        boolean checkdate = dt_requestDate.isAfter(dt_dateFrom) && dt_requestDate.isBefore(dt_dateTo);
        System.out.println("check date for between " + checkdate);
        //Date todayDate = new Date();
        boolean checkuppercieling = request_date.equals(date_from);
        boolean checklowercieling = request_date.equals(date_to);
        return checkdate || checkuppercieling || checklowercieling;
    }catch (ParseException e){
        e.printStackTrace();
        return false;
    }
    }


    @Override
    public BigDecimal calculateTotalDailySalesAmount(List<SalesReport> salesReportList) {
        double totalAmount = 0.0;
        for (SalesReport sales : salesReportList){
            totalAmount = totalAmount + sales.getPlay_cost().doubleValue() + sales.getAdditional_cost().doubleValue();
        }
        return new BigDecimal(totalAmount);
    }

    @Override
    public Bill getBillById(int bill_id) {
        return billDao.getOne(bill_id);
    }
}
