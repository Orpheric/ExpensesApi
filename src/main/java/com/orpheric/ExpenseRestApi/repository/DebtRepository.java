package com.orpheric.ExpenseRestApi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orpheric.ExpenseRestApi.model.Debt;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
		List<Debt> findByCreditor(String Creditor);
		List<Debt> findByDate(LocalDate date);
		Debt findByIdAndUserId(Long id,Long userId);
		List<Debt> findByUserId(Long userId);
		List<Debt> findByDateDue(LocalDate dateDue);
		
}
