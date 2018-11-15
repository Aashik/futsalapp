package com.futsalmanagement.futsalapp.service;

import com.futsalmanagement.futsalapp.dao.ExpenseDao;
import com.futsalmanagement.futsalapp.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public Expense insert(Expense expense) {
        Expense insertedExpense = expenseDao.save(expense);
        return insertedExpense;
    }
}
