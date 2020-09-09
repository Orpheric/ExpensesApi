package com.orpheric.ExpenseRestApi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orpheric.ExpenseRestApi.model.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long>{
		
	List<Balance> findByUserId(Long userId);
	Balance findFirstByUserIdOrderByDateDesc(Long userId);
		
}
