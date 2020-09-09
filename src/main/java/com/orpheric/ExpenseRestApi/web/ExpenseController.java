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
import org.springframework.web.bind.annotation.RestController;

import com.orpheric.ExpenseRestApi.model.Expense;
import com.orpheric.ExpenseRestApi.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	public ExpenseController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping
	public ResponseEntity<Expense> createExpenseMethod(@RequestBody Expense expense)
	{
		if(expense==null)
		{
			return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
		}
		else
		{
			try{


				Expense expenseCreated = 
						expenseService.createExpense(expense.getUser().getId(),expense.getFee(),expense.getTitle());
				if(expenseCreated==null)
				{
					return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
				}
				else
				{
					return new ResponseEntity<Expense>(expenseCreated,HttpStatus.OK);

				}
			}
			catch(EntityNotFoundException e)
			{
				return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
			}
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Expense> getExpenseById(@PathVariable Long id)
	{
		if(id!=null)
		{

			Expense expense = expenseService.findExpenseById(id);
			if(expense==null)
			{
				return new ResponseEntity<Expense>(HttpStatus.NO_CONTENT);
			}
			else
			{
				return new ResponseEntity<Expense>(expense,HttpStatus.OK);
			}

		}
		return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Expense> updateExpense(@PathVariable Long id,@RequestBody Expense expense)
	{
		if(expense==null)
		{
			return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
		}
		else
		{
			try{


				Expense expenseUpdated = expenseService.updateExpense(id,expense.getFee(),expense.getTitle());
				if(expenseUpdated==null)
				{
					return new ResponseEntity<Expense>(HttpStatus.NOT_MODIFIED);
				}
				else
				{
					return new ResponseEntity<Expense>(expenseUpdated,HttpStatus.OK);
				}
			}
			catch(EntityNotFoundException e)
			{
				return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
			}
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Expense> deleteExpense(@PathVariable Long id)
	{
		if(id!=null)
		{
			try{
				expenseService.deleteExpense(id);
				return new ResponseEntity<Expense>(HttpStatus.OK); 
			}
			catch(EntityNotFoundException e)
			{
				return new ResponseEntity<Expense>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<Expense>(HttpStatus.BAD_REQUEST);
	}

}
