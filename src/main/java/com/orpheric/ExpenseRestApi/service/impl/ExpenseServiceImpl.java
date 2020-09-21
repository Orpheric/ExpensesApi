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

	public Expense createUserExpense(Long userId,Long fee,String title) {
		// TODO Auto-generated method stub
		if(userId!=null)
		{
			if(fee!=null)
			{
				if(userRepo.findById(userId).isPresent())
				{
					
					Expense expenseCreated = new Expense(title,fee,LocalDate.now(),userRepo.findById(userId).get());
					//Save balance
					Long latestBalance = balanceRepo.findFirstByUserIdOrderByDateDesc(userId).getAmount();
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

	public Expense updateUserExpense(Long userId,Long id,Long fee, String title) throws EntityNotFoundException{
		// TODO Auto-generated method stud
		if(expenseRepo.findByIdAndUserId(id, userId)!=null)
		{
			if(fee!=null)
			{
				Expense expenseUpdated = expenseRepo.findByIdAndUserId(id, userId);
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

	public Expense findUserExpenseById(Long id,Long userId) {
		// TODO Auto-generated method stub
		if(expenseRepo.findByIdAndUserId(id, userId)!=null)
		{
			return expenseRepo.findByIdAndUserId(id, userId);
		}
		return null;
	}

	public void deleteUserExpense(Long id,Long userId) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if(expenseRepo.findByIdAndUserId(id, userId)!=null)
		{
			
			expenseRepo.delete(expenseRepo.findByIdAndUserId(id, userId)); 
		}
		else
		{
			throw new EntityNotFoundException();
		}
	}

}
