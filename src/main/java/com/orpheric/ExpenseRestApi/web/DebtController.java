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
import org.springframework.web.bind.annotation.RestController;

import com.orpheric.ExpenseRestApi.model.Debt;
import com.orpheric.ExpenseRestApi.service.DebtService;

@RestController
@RequestMapping("users/{userId}/debts")
public class DebtController {

	@Autowired
	DebtService debtService;
	
	public DebtController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostMapping
	public ResponseEntity<Debt> createDebtMethod(@PathVariable Long userId,@RequestBody Debt debt)
	{
		if(debt!=null)
		{
			try{
				Debt debtCreated =
				debtService.createUserDebt(userId, debt.getTitle(), debt.getCreditor(), debt.getAmount(), debt.getDateDue());
				if(debtCreated==null)
				{
					return new ResponseEntity<Debt>(HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<Debt>(debtCreated,HttpStatus.OK);
				
			}
			catch(EntityNotFoundException e)
			{
				return  new ResponseEntity<Debt>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<Debt>(HttpStatus.BAD_REQUEST);
		
	}
	
	 @GetMapping
	 public ResponseEntity<List<Debt>>getAllUserDebts(@PathVariable Long userId)
	 {
		 if(userId!=null)
		 {
			 try
			 {
				 List<Debt> debts = debtService.findAllUserDebts(userId);
				 if(debts.isEmpty())
				 {
					 return new ResponseEntity<List<Debt>>(HttpStatus.NO_CONTENT);
				 }
				 else
				 {
					 return new ResponseEntity<List<Debt>>(debts,HttpStatus.OK);
				 } 
			 }
			 catch(EntityNotFoundException e)
			 {
				 return new ResponseEntity<List<Debt>>(HttpStatus.NOT_FOUND);
			 }
			 
		 }
		 return new ResponseEntity<List<Debt>>(HttpStatus.BAD_REQUEST);
	 }
	 
	 @GetMapping("/{debtId}")
	 public ResponseEntity<Debt>getDebtById(@PathVariable Long userId,@PathVariable Long debtId)
	 {
		 if(userId!=null&& debtId!=null)
		 {
			 Debt debt = debtService.findUserDebt(userId, debtId);
			 if(debt==null)
			 {
				 return new ResponseEntity<Debt>(HttpStatus.NOT_FOUND);
			 }
			 else
			 {
				 return new ResponseEntity<Debt>(debt,HttpStatus.OK);
			 }
		 }
		 return new ResponseEntity<Debt>(HttpStatus.BAD_REQUEST);
		 
	 }
		 
	 
	 
	 @PutMapping("/{debtId}")
	 public ResponseEntity<Debt> updateUserDebtMethod(@PathVariable Long userId,@PathVariable Long debtId,@RequestBody Debt debt)
	 {
		 if(userId!=null&& debtId!=null)
		 {
			 try{
				 Debt debtUpdated= debtService.updateUserDebt(userId, debtId, debt.getTitle(), debt.getCreditor(), debt.getDateDue());
				 if(debtUpdated!=null)
				 {
					 return new ResponseEntity<Debt>(debtUpdated,HttpStatus.OK);
				 }
				 return new ResponseEntity<Debt>(HttpStatus.NOT_MODIFIED);
			 }
			 catch(EntityNotFoundException e)
			 {
				 return new ResponseEntity<Debt>(HttpStatus.NOT_FOUND);
			 }
		 }
		 return new ResponseEntity<Debt>(HttpStatus.BAD_REQUEST);
	 }
	 
	 @DeleteMapping("/{debtId}")
	 public ResponseEntity<Debt> deletingUserDebtMethod(@PathVariable Long userId,@PathVariable Long debtId)
	 {
		 if(userId!=null&& debtId!=null)
		 {
			 try{
				 debtService.deleteUserDebt(userId, debtId);
				 return new ResponseEntity<Debt>(HttpStatus.OK);
			 }
			 catch(EntityNotFoundException e)
			 {
				 return new ResponseEntity<Debt>(HttpStatus.NOT_FOUND);
			 }
		 }
		 return new ResponseEntity<Debt>(HttpStatus.BAD_REQUEST);
		 
	 }
	 

}
