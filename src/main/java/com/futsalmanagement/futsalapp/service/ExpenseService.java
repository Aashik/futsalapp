package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense insert(Expense expense);
    List<Expense> getAllExpensesByGame(int game_id);
}
