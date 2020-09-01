package com.orpheric.ExpenseRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orpheric.ExpenseRestApi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
		
	public User findByLogin(String login);
	public User findByPhone(String phone);
	
}
