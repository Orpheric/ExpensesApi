package com.orpheric.ExpenseRestApi.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orpheric.ExpenseRestApi.model.Balance;
import com.orpheric.ExpenseRestApi.model.Income;
import com.orpheric.ExpenseRestApi.repository.BalanceRepository;
import com.orpheric.ExpenseRestApi.repository.IncomeRepository;
import com.orpheric.ExpenseRestApi.repository.UserRepository;
import com.orpheric.ExpenseRestApi.service.IncomeService;


@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BalanceRepository balanceRepo;
	
	public IncomeServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	public List<Income> getAllIncomesByUserId(Long id) throws EntityNotFoundException{
		// TODO Auto-generated method stub
		if(id==null){
			throw new EntityNotFoundException();
		}
		else
		{
			return incomeRepo.findByUserId(id);
		}
	}
	public Income saveUserIncome(Long userId, Long amount, String title) throws EntityNotFoundException {
		// TODO Auto-generated method stub
		if(userId!=null && amount!=null)
		{
			if(userRepo.findById(userId).isPresent())
			{
				Income incomeCreated = new Income(title,amount,LocalDate.now(),userRepo.findById(userId).get());
				//Save balance
				Long latestBalance = balanceRepo.findFirstByUserIdOrderByDateDesc(userId).getAmount();
				Balance balanceUpdated = new Balance(latestBalance+amount,LocalDateTime.now(),userRepo.findById(userId).get());
				balanceRepo.save(balanceUpdated);
				return incomeRepo.save(incomeCreated);
			}
			throw new EntityNotFoundException();

		}
		return null;
	}
	public Income updateUserIncome(Long userId,Long incomeId, Long amount, String title) throws EntityNotFoundException{
		// TODO Auto-generated method stub

		if((incomeRepo.findByIdAndUserId(incomeId, userId)!=null))
		{
			Income income = (incomeRepo.findByIdAndUserId(incomeId, userId));

			income.setAmount(amount);
			income.setTitle(title);
			return incomeRepo.save(income);

		}
		else
		{
			throw new EntityNotFoundException();

		}

	}
	public void deleteUserIncome(Long incomeId,Long userId) {
		// TODO Auto-generated method stub
		if(incomeRepo.findByIdAndUserId(incomeId, userId)!=null)
		{
			Income income = incomeRepo.findByIdAndUserId(incomeId, userId);

			incomeRepo.delete(income);


		}
		else
		{
			throw new EntityNotFoundException();

		}


	}
	public Income findIncomeById(Long id) {
		// TODO Auto-generated method stub
		if(incomeRepo.findById(id).isPresent())
		{
			return incomeRepo.findById(id).get();
		}
		return null;
	}
	public Income findUserIncomeById(Long userId, Long incomeId) {
		// TODO Auto-generated method stub
		if(userId==null){
			throw new EntityNotFoundException();
		}
		else
		{
			return this.incomeRepo.findByIdAndUserId(incomeId, userId);
		}
	}
}




