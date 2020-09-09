package com.orpheric.ExpenseRestApi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orpheric.ExpenseRestApi.model.Balance;
import com.orpheric.ExpenseRestApi.repository.BalanceRepository;
import com.orpheric.ExpenseRestApi.service.BalanceService;

@Service
public class BalanceServiceImpl implements BalanceService{

	@Autowired
	BalanceRepository balanceRepo;
	
	public BalanceServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Balance> getAllUsersBalance(Long userId) {
		// TODO Auto-generated method stub
		if(userId!=null)
		{
			return balanceRepo.findByUserId(userId);
		}
		return null;
	}

	public Balance getBalance(Long id) {
		// TODO Auto-generated method stub
		if(id!=null)
		{
			if(balanceRepo.findById(id).isPresent())
			{
				return balanceRepo.findById(id).get();
			}
			return null;
		}
		return null;
	}

}
