package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Expense;
import java.util.List;
import java.util.Map;

public interface ExpenseService {

    Expense insert(Expense expense);
    List<Expense> getAllExpensesByGame(int game_id);
    List<Map<String,Object>> getInGenericExpense(List<Expense> expenseList);
}
