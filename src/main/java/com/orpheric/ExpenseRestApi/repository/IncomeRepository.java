package com.orpheric.ExpenseRestApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orpheric.ExpenseRestApi.model.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long>{
	public List<Income> findByUserId(Long id);
	
}
