package com.orpheric.ExpenseRestApi.web;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orpheric.ExpenseRestApi.model.Balance;
import com.orpheric.ExpenseRestApi.service.BalanceService;

@RestController
@RequestMapping("/users/{userId}/")
public class BalanceController {

	@Autowired
	BalanceService balanceService;
	
	public BalanceController() {
		// TODO Auto-generated constructor stub
	}

	 @GetMapping("/balances")
	 public ResponseEntity<List<Balance>> getUserAllBalances(@PathVariable Long userId)
	 {
		 try
		 {
			 if(userId!=null)
			 {
				 List<Balance> balances = balanceService.getAllUsersBalance(userId);
				 if(balances.isEmpty())
				 {
					 return  new ResponseEntity<List<Balance>>(HttpStatus.NO_CONTENT);
				 }
				 else
				 {
					 return  new ResponseEntity<List<Balance>>(balances,HttpStatus.OK);
				 }
			 }
			 return new ResponseEntity<List<Balance>>(HttpStatus.BAD_REQUEST);
		 }
		 catch(EntityNotFoundException e)
		 {
			 return new ResponseEntity<List<Balance>>(HttpStatus.NOT_FOUND);
		 }
		 
		 
	 }
	 
	 @GetMapping("/balance")
	 public ResponseEntity<Balance> getUserBalance(@PathVariable Long userId)
	 {
		 	if(userId!=null)
			 {
				 	Balance balance = balanceService.getLatestBalance(userId);
				 
					 return  new ResponseEntity<Balance>(balance,HttpStatus.OK);
				
			 }
			 return new ResponseEntity<Balance>(HttpStatus.BAD_REQUEST);
		
		 
		 
	 }

}
