package com.orpheric.ExpenseRestApi.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orpheric.ExpenseRestApi.model.Balance;
import com.orpheric.ExpenseRestApi.model.Expense;
import com.orpheric.ExpenseRestApi.repository.BalanceRepository;
import com.orpheric.ExpenseRestApi.repository.ExpenseRepository;
import com.orpheric.ExpenseRestApi.repository.UserRepository;
import com.orpheric.ExpenseRestApi.service.ExpenseService;

@Service
public class ExpenseServiceImpl implements ExpenseService{

	@Autowired
	ExpenseRepository expenseRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BalanceRepository balanceRepo;
	
	
	public ExpenseServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Expense createExpense(Long userId,Long fee,String title) {
		// TODO Auto-generated method stub
		if(userId!=null)
		{
			if(fee!=null)
			{
				if(userRepo.findById(userId).isPresent())
				{
					Expense expenseCreated = new Expense(title,fee,LocalDate.now(),userRepo.findById(userId).get());
					//Save balance
					Long latestBalance = balanceRepo.findTopByOrderByIdDesc().getAmount();
					Balance balanceUpdated = new Balance(latestBalance-fee,LocalDateTime.now(),userRepo.findById(userId).get());
					balanceRepo.save(balanceUpdated);
					return expenseRepo.save(expenseCreated);
				}
				throw new EntityNotFoundException();
			}
			return null;
		}
		throw new EntityNotFoundException();
	}

	public Expense updateExpense(Long id,Long fee, String title) throws EntityNotFoundException{
		// TODO Auto-generated method stud
		if(expenseRepo.findById(id).isPresent())
		{
			if(fee!=null)
			{
				Expense expenseUpdated = expenseRepo.findById(id).get();
				expenseUpdated.setFee(fee);
				expenseUpdated.setTitle(title);
				return expenseRepo.save(expenseUpdated);
			}
			return null;
			
		}
		throw new EntityNotFoundException();
		
		
	}

	public List<Expense> getAllUserExpenses(Long userId) {
		// TODO Auto-generated method stub
		if(userId ==null)
		{
			return null;
		}
		return expenseRepo.findByUserId(userId);
	}

	public Expense findExpenseById(Long id) {
		// TODO Auto-generated method stub
		if(expenseRepo.findById(id).isPresent())
		{
			return expenseRepo.findById(id).get();
		}
		return null;
	}

	public void deleteExpense(Long id) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if(expenseRepo.findById(id).isPresent())
		{
			expenseRepo.deleteById(id);
		}
		else
		{
			throw new EntityNotFoundException();
		}
	}

}
