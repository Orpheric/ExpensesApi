package com.orpheric.ExpenseRestApi.web;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orpheric.ExpenseRestApi.model.Balance;
import com.orpheric.ExpenseRestApi.model.Expense;
import com.orpheric.ExpenseRestApi.model.Income;
import com.orpheric.ExpenseRestApi.model.User;
import com.orpheric.ExpenseRestApi.model.Exception.UniquePropertyDuplicatedException;
import com.orpheric.ExpenseRestApi.service.BalanceService;
import com.orpheric.ExpenseRestApi.service.ExpenseService;
import com.orpheric.ExpenseRestApi.service.IncomeService;
import com.orpheric.ExpenseRestApi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		try{


			if(user==null)
			{
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
			}
			else
			{
				User userCreate = userService.createUser(user.getPhone(),user.getLogin(), user.getPassword(), user.getName());
				if(userCreate==null)
				{
					return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
				}
				else
				{
					return new ResponseEntity<User>(userCreate,HttpStatus.CREATED);
				}

			}
		}
		catch(UniquePropertyDuplicatedException e)
		{
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/{userLogin}")
	public ResponseEntity<User> searchUserWithHisLogin(@PathVariable String userLogin)
	{
		if(userLogin!=null)
		{
			User userSearched = userService.findUserByLogin(userLogin);
			if(userSearched==null)
			{
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<User>(userSearched, HttpStatus.OK);
			}
		}
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUserPassword(@RequestParam String newPassword ,@PathVariable Long id)
	{
		try{


			if(id==null&&newPassword==null)
			{
				return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
			}
			else
			{
				User userUpdated = userService.updateUserPasswordWithId(id, newPassword);
				if(userUpdated==null)
				{
					return new ResponseEntity<User>(HttpStatus.NOT_MODIFIED);
				}
				else
				{
					return new ResponseEntity<User>(userUpdated,HttpStatus.OK);
				}
			}
		}
		catch(EntityNotFoundException e)
		{
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id)
	{
		if(id!=null)
		{
			try{
				userService.deleteUser(id);
				return new ResponseEntity<User>(HttpStatus.OK);

			}catch(EntityNotFoundException e)
			{
				return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
			}


		}
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}

	
	
	
}
