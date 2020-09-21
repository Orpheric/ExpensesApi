package com.orpheric.ExpenseRestApi.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.orpheric.ExpenseRestApi.model.Income;



public interface IncomeService {

	public List<Income> getAllIncomesByUserId(Long id) throws EntityNotFoundException;
	public Income findIncomeById(Long id);
	public Income findUserIncomeById(Long userId,Long incomeId);
	public Income saveUserIncome(Long userId,Long amount,String title)  throws EntityNotFoundException;
	public Income updateUserIncome(Long userId,Long incomeId,Long amount,String title)  throws EntityNotFoundException;
	public void deleteUserIncome(Long incomeId,Long userId)  throws EntityNotFoundException;
}
