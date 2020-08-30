package com.orpheric.ExpenseRestApi.service;

import javax.persistence.EntityNotFoundException;

import com.orpheric.ExpenseRestApi.model.User;
import com.orpheric.ExpenseRestApi.model.Exception.UniquePropertyDuplicatedException;

public interface UserService {

	public User createUser(String phone,String login,String password, String name) throws UniquePropertyDuplicatedException;
	public User findUserByLogin(String login);
	public User updateUserPasswordWithLogin(String login,String newPassword) throws EntityNotFoundException;
	public User updateUserPasswordWithId(Long id, String newPassword) throws EntityNotFoundException;
	public void deleteUser(String login) throws EntityNotFoundException;
	public void deleteUser(Long id) throws EntityNotFoundException;
}
