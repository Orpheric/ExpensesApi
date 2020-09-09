package com.orpheric.ExpenseRestApi.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.orpheric.ExpenseRestApi.model.Expense;

public interface ExpenseService {
	public Expense createExpense(Long userId,Long fee,String title) throws EntityNotFoundException;
	public Expense updateExpense(Long id,Long fee, String title) throws EntityNotFoundException;
	public List<Expense> getAllUserExpenses(Long userId) throws EntityNotFoundException;
	public Expense findExpenseById(Long id);
	public void deleteExpense(Long id) throws EntityNotFoundException;
	
}
