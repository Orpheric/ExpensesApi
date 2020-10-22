package com.orpheric.ExpenseRestApi.service;

import java.time.LocalDate;
import java.util.List;

import com.orpheric.ExpenseRestApi.model.Debt;

public interface DebtService {
		public Debt createUserDebt(Long userId,String title, String creditor,Long amount,LocalDate dateDue);
		public List<Debt> findAllUserDebts(Long userId);
		public Debt findUserDebt(Long userId,Long id);
		public Debt updateUserDebt(Long userId,Long id,String title, String creditor,LocalDate dateDue);
		public void deleteUserDebt(Long userId, Long id);
}
