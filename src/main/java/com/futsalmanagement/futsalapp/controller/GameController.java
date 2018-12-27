package com.futsalmanagement.futsalapp.controller;

import com.futsalmanagement.futsalapp.entity.Customer;
import com.futsalmanagement.futsalapp.entity.Expense;
import com.futsalmanagement.futsalapp.entity.Game;
import com.futsalmanagement.futsalapp.entity.Menu;
import com.futsalmanagement.futsalapp.model.GlobalResponse;
import com.futsalmanagement.futsalapp.model.Status;
import com.futsalmanagement.futsalapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private FutsalService futsalService;
    @Autowired
    private GroundService groundService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "api/addDirectGame", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addDirectGame(@RequestBody Game game,
                                                        @RequestParam("futsal_id") int futsal_id,
                                                        @RequestParam("ground_id") int ground_id){
        String game_date = game.getPlay_date();
        String game_time = game.getPlay_start_time();
        if (futsalService.checkFutsalAvailability(futsal_id) && groundService.checkGroundAvailability(futsal_id, ground_id)) {
            if (gameService.checkifTodayDate(game_date) && gameService.checkifValidTime(game_date, game_time)) {

                game.setGame_type(Status.DIRECT_ENTRY.toString());
                game.setGame_status(Status.STARTED.toString());
                game.setPlayFutsal(futsalService.getFutsalById(futsal_id));
                game.setPlayGround(groundService.getGroundById(futsal_id, ground_id));

                Customer foundCustomer = customerService.ifPresentCustomer(game.getCustomer().getContact_number(), futsal_id);
                Customer toAddCustomer = null;
                if (foundCustomer != null) {
                    int play_count = foundCustomer.getCustomer_play_count();
                    foundCustomer.setCustomer_play_count(play_count + 1);
                    toAddCustomer = customerService.addCustomer(foundCustomer);
                } else {
                    Customer newCustomer = game.getCustomer();
                    newCustomer.setCustomer_play_count(1);
                    newCustomer.setFutsal(futsalService.getFutsalById(futsal_id));
                    toAddCustomer = customerService.addCustomer(newCustomer);
                }
                if (toAddCustomer != null) {
                    game.setCustomer(toAddCustomer);
                    Game insertedGame = gameService.addGame(game);
                    if (insertedGame != null) {
                        GlobalResponse response = new GlobalResponse(Status.SUCCESS, "game started successfully", insertedGame);
                        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                    }
                    GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "cannot insert. In61valid request ", null);
                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                }
                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "cannot insert. Invalid request ", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid Date or time ", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid Futsal Id or Ground Id", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/addExpenses", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addGameExpense(@RequestBody List<Map<String, Object>> expenseList,
                                                         @RequestParam("game_id") int game_id) {
        Game game = gameService.findGameById(game_id);

        if (game != null) {
            if (gameService.checkifTodayDate(game.getPlay_date())
                    && !(game.getGame_status().equals(Status.COMPLETED.toString()))) {
                int count = 0;
                for (Map<String, Object> expenseObj : expenseList) {
                    //Expense inserted_expense = expenseService.insert(expense);
                    int menu_id = Integer.parseInt(expenseObj.get("menu_id").toString());
                    int quantity = Integer.parseInt(expenseObj.get("quantity").toString());
                    Menu menu = menuService.findMenuById(menu_id);
                    BigDecimal each_amount = menu.getUnit_price().multiply(new BigDecimal(quantity));
                    Expense to_persist_expense = new Expense(menu, quantity, each_amount, game);
                    Expense inserted_expense = expenseService.insert(to_persist_expense);
                    count = count + (inserted_expense != null ? 1 : 0);
                }
                if (expenseList.size() == count) {
                    GlobalResponse response = new GlobalResponse(Status.SUCCESS, "added expense successfully", null);
                    return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
                }
                GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "All data not inserted.", null);
                return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
            }
            GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid date or time. cannot add expense", null);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "Invalid game id. Please try again", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    //api to add menu item
    @RequestMapping(value = "api/addMenu", method = RequestMethod.POST)
    public ResponseEntity<GlobalResponse> addMenu(@RequestBody Menu menu) {
        Menu inserted_menu = menuService.addMenu(menu);
        if (inserted_menu != null) {
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "menu added successfully", inserted_menu);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.SYSTEM_ERROR, "Couldnot perform operation", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "api/getExpensesByGame", method = RequestMethod.GET)
    public ResponseEntity<GlobalResponse> getAllExpensesByGame(@RequestParam("game_id") int game_id) {

        List<Expense> expenses = expenseService.getAllExpensesByGame(game_id);
        if (expenses != null && expenses.size() >= 1) {
            List<Expense> expenseList = expenses.stream()
                    .map(expense -> expense.inSimpleFormat()).collect(Collectors.toList());
            GlobalResponse response = new GlobalResponse(Status.SUCCESS, "expenses of specified game fetched", expenseList);
            return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);
        }
        GlobalResponse response = new GlobalResponse(Status.DATA_ERROR, "No any expense available for particular game", null);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.OK);

    }


}
