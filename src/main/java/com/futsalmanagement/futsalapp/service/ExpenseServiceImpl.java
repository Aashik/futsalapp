package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.ExpenseDao;
import com.futsalmanagement.futsalapp.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public Expense insert(Expense expense) {
        Expense insertedExpense = expenseDao.save(expense);
        return insertedExpense;
    }

    @Override
    public List<Expense> getAllExpensesByGame(int game_id) {
        return expenseDao.findAll().stream()
                .filter(expense -> expense.getGame().getGame_id() == game_id).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> getInGenericExpense(List<Expense> expenseList) {
        List<Map<String,Object>> expenses = new ArrayList<>();
        for (Expense expense : expenseList){
            Map<String,Object> expenseobj = new HashMap<>();
            expenseobj.put("item",expense.getMenu_item().getMenu_item());
            expenseobj.put("quantity", expense.getQuantity());
            expenseobj.put("rate", expense.getMenu_item().getUnit_price());
            expenseobj.put("amount", expense.getAmount());
            expenses.add(expenseobj);
        }
        return expenses;
    }
}
