package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.DiscountDao;
import com.futsalmanagement.futsalapp.entity.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDao discountDao;

    @Override
    public Discount addDiscount(Discount d) {
        Discount discount = discountDao.save(d);
        return discount;
    }

    @Override
    public List<Discount> getAllDiscount() {
        return discountDao.findAll();
    }

    @Override
    public boolean isDiscountAvailable(int futsal_id, int ground_id, String play_date) {
        Discount foundDiscount = discountDao.findAll().stream().filter(discount -> discount.getFutsal().getFutsal_id() == futsal_id &&
                                               discount.getGround().getGround_id() == ground_id).findAny().orElse(null);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date futsalDate = sdf.parse(play_date);
            Calendar c = Calendar.getInstance();
            c.setTime(futsalDate);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            System.out.println("calculated day of week " + dayOfWeek);
            int[] discountdays = foundDiscount.getDiscount_weekdays();
            for (int i = 0 ; i<discountdays.length ; i++){
                System.out.println("in loop checking for " + discountdays[i]);
                boolean checkparam = dayOfWeek == discountdays[i];
                System.out.println("check " + checkparam);
                if (dayOfWeek == discountdays[i])
                    return true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Discount getDiscountById(int futsal_id, int ground_id) {

        Discount foundDiscount = getAllDiscount().stream()
                .filter(discount -> discount.getFutsal().getFutsal_id() == futsal_id && discount.getGround().getGround_id() == ground_id)
                .findAny()
                .orElse(null);
        return foundDiscount;
    }
}
