package com.orpheric.ExpenseRestApi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orpheric.ExpenseRestApi.model.Expense;
import com.orpheric.ExpenseRestApi.model.Income;
import com.orpheric.ExpenseRestApi.model.User;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
		
	List<Expense> findByTitle(String title);
	List<Expense> findByDate(LocalDate date);
	List<Expense> findByUserId(Long userId);
	Expense findByIdAndUserId(Long id, Long userId);
	

}
