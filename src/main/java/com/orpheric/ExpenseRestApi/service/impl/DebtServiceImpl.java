package com.orpheric.ExpenseRestApi.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orpheric.ExpenseRestApi.model.Debt;
import com.orpheric.ExpenseRestApi.model.DebtStatus;
import com.orpheric.ExpenseRestApi.model.User;
import com.orpheric.ExpenseRestApi.repository.DebtRepository;
import com.orpheric.ExpenseRestApi.repository.UserRepository;
import com.orpheric.ExpenseRestApi.service.DebtService;

@Service
public class DebtServiceImpl implements DebtService{

	@Autowired
	DebtRepository debtRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public DebtServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public Debt createUserDebt(Long userId, String title, String creditor,Long amount, LocalDate dateDue) {
		// TODO Auto-generated method stub
		if(userId!=null)
		{
			if(creditor!=null && amount!=null)
			{
				if(userRepo.findById(userId)!=null)
				{
					User user= userRepo.findById(userId).get();
					Debt debtCreated = new Debt(title, creditor, amount, LocalDate.now(), user, DebtStatus.ON.name(), dateDue);
					return debtRepo.save(debtCreated);
				}
				throw new EntityNotFoundException();
			}
			return null;
			
		}
		
		throw new EntityNotFoundException();
	}

	public List<Debt> findAllUserDebts(Long userId) {
		// TODO Auto-generated method stub
		return debtRepo.findByUserId(userId);
	}

	public Debt findUserDebt(Long userId, Long id) {
		// TODO Auto-generated method stub
		return debtRepo.findByIdAndUserId(id, userId);
	}

	public Debt updateUserDebt(Long userId, Long id, String title, String creditor, LocalDate dateDue) {
		// TODO Auto-generated method stub
		if(debtRepo.findByIdAndUserId(id, userId)!=null)
		{
			Debt debtUpdated = debtRepo.findByIdAndUserId(id, userId);
			debtUpdated.setCreditor(creditor);
			debtUpdated.setDateDue(dateDue);
			debtUpdated.setTitle(title);
			return debtRepo.save(debtUpdated);
		}
		throw new EntityNotFoundException();
	}

	public void deleteUserDebt(Long userId, Long id) {
		// TODO Auto-generated method stub
		Debt debtDelete = debtRepo.findByIdAndUserId(id, userId);
		if(debtDelete!=null)
		{
			debtRepo.delete(debtDelete);
		}
		throw new EntityNotFoundException();
		
	}

}
