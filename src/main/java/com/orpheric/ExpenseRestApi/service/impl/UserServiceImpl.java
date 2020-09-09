package com.orpheric.ExpenseRestApi.service.impl;

import java.time.LocalDateTime;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orpheric.ExpenseRestApi.model.Balance;
import com.orpheric.ExpenseRestApi.model.User;
import com.orpheric.ExpenseRestApi.model.Exception.UniquePropertyDuplicatedException;
import com.orpheric.ExpenseRestApi.repository.BalanceRepository;
import com.orpheric.ExpenseRestApi.repository.UserRepository;
import com.orpheric.ExpenseRestApi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BalanceRepository balanceRepo;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	public User createUser(String phone, String login, String password, String name) throws UniquePropertyDuplicatedException {
		// TODO Auto-generated method stub
		if(phone==null&&login==null&&password==null&&name==null)
		{
			return null;
		}
		else
		{
			User userCreate = userRepo.findByLogin(login);
			if(userCreate==null)
			{
				User user = new User(name,login,password,phone);
				User userCreated = userRepo.save(user);
				balanceRepo.save(new Balance(0L,LocalDateTime.now(),userCreated));
				return userCreated;

			}
			else
			{
				throw new UniquePropertyDuplicatedException();
			}
		}

	}

	public User findUserByLogin(String login) {
		// TODO Auto-generated method stub
		return userRepo.findByLogin(login);
	}

	public User updateUserPasswordWithLogin(String login, String newPassword) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if(login ==null)
		{
			return null;	
		}
		else
		{
			User user = findUserByLogin(login);
			if(user==null)
			{
				throw new EntityNotFoundException();
			}
			else{
				user.setPassword(newPassword);
				return userRepo.save(user);
			}


		}

	}

	public User updateUserPasswordWithId(Long id, String newPassword) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if(id ==null)
		{
			return null;	
		}
		else
		{
			if( !userRepo.findById(id).isPresent())
			{
				throw new EntityNotFoundException();
			}
			else{
				User user = userRepo.findById(id).get();
				user.setPassword(newPassword);
				return userRepo.save(user);
			}


		}

	}

	public void deleteUser(String login) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		if(login==null)
		{
			throw new EntityNotFoundException();
		}
		else
		{
			User user = findUserByLogin(login);
			if(user==null)
			{
				throw new EntityNotFoundException();
			}
			else
			{
				 userRepo.delete(user);
			}
		}
		
	}
	public void deleteUser(Long id) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		if(id==null)
		{
			throw new EntityNotFoundException();
		}
		else
		{
			
			if( !userRepo.findById(id).isPresent())
			{
				throw new EntityNotFoundException();
			}
			else
			{
				User user =  userRepo.findById(id).get();
				 userRepo.delete(user);
			}
		}
		
	}


}
