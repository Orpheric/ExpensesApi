package com.orpheric.ExpenseRestApi.web;

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

import com.orpheric.ExpenseRestApi.model.Income;
import com.orpheric.ExpenseRestApi.service.IncomeService;

@RestController
@RequestMapping("/incomes")
public class IncomeController {

	@Autowired IncomeService incomeService;
	
	public IncomeController() {
		// TODO Auto-generated constructor stub
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Income> getIncomeById(@PathVariable Long id)
	{
		if(id!=null)
		{
			Income income = incomeService.findIncomeById(id);
			if(income!=null)
			{
				return new ResponseEntity<Income>(income,HttpStatus.OK);
			}
			return new ResponseEntity<Income>(HttpStatus.NO_CONTENT);
			
		}
		else
		{
			return new ResponseEntity<Income>(HttpStatus.BAD_REQUEST);
		}

	}
	@PostMapping
	public ResponseEntity<Income> saveUserIncome(@RequestBody Income income)
	{
		try{
			
	
		if(income!=null)
		{
			Income incomecreated = incomeService.saveUserIncome(
					income.getUser().getId(),income.getAmount(), income.getTitle());
			if(incomecreated!=null)
			{
				return new ResponseEntity<Income>(incomecreated,HttpStatus.CREATED);
			}
			else
			{
				return new ResponseEntity<Income>(HttpStatus.NOT_MODIFIED);
			}
			
			
		}
		return new ResponseEntity<Income>(HttpStatus.NOT_FOUND);
		
		}
		catch(EntityNotFoundException e)
		{
			return new ResponseEntity<Income>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Income> deletingIncomeMethod(@PathVariable Long id)
	{
		if(id==null)
		{
			return new ResponseEntity<Income>(HttpStatus.NOT_FOUND);
		}
		else
		{
			try{
				incomeService.deleteUserIncome(id);
				return new ResponseEntity<Income>(HttpStatus.OK);
			}
			catch(EntityNotFoundException e)
			{
				return new ResponseEntity<Income>(HttpStatus.NOT_FOUND);
			}
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Income> updateIncomeInfo(@PathVariable Long id, @RequestBody Income income)
	{
		if(id!=null && income !=null)
		{
			try{
				Income incomeUpdated = incomeService.updateUserIncome(id, income.getAmount(), income.getTitle());
				if(incomeUpdated==null){
					return  new ResponseEntity<Income>(HttpStatus.NOT_MODIFIED);
				}
				else
				{
					return new ResponseEntity<Income>(incomeUpdated,HttpStatus.CREATED);
				}
			}
			catch(EntityNotFoundException e)
			{
				return new ResponseEntity<Income>(HttpStatus.NOT_FOUND);
			}
		}
		else
		{
			return new ResponseEntity<Income>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
