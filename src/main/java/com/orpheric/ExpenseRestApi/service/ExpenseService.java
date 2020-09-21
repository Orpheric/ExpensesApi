package com.orpheric.ExpenseRestApi.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.orpheric.ExpenseRestApi.model.Expense;

public interface ExpenseService {
	public Expense createUserExpense(Long userId,Long fee,String title) throws EntityNotFoundException;
	public Expense updateUserExpense(Long userId,Long id,Long fee, String title) throws EntityNotFoundException;
	public List<Expense> getAllUserExpenses(Long userId) throws EntityNotFoundException;
	public Expense findUserExpenseById(Long id,Long userId);
	public void deleteUserExpense(Long id,Long userId) throws EntityNotFoundException;
	
}
