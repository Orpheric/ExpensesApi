package com.orpheric.ExpenseRestApi.service;

import java.util.List;

import com.orpheric.ExpenseRestApi.model.Balance;

public interface BalanceService {
		
		public List<Balance> getAllUsersBalance(Long userId);
		public Balance getBalance(Long id);
}
