package com.orpheric.ExpenseRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orpheric.ExpenseRestApi.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
		
	public User findByLogin(String login);
	public User findByPhone(String phone);
	
	
}
