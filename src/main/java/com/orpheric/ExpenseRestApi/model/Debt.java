package com.orpheric.ExpenseRestApi.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Debt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	@Column(nullable=false)
	private String creditor;
	
	@Column(nullable=false)
	private Long amount;
	@Column(nullable=false)
	private LocalDate date;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName="id",name="user_id")
	private User user;
	private String status;
	private LocalDate dateDue;

	public Debt() {
		// TODO Auto-generated constructor stub
	}


	public Debt(String title, String creditor, Long amount, LocalDate date, User user, String status,
			LocalDate dateDue) {
		super();
		this.title = title;
		this.creditor = creditor;
		this.amount = amount;
		this.date = date;
		this.user = user;
		this.status = status;
		this.dateDue = dateDue;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreditor() {
		return creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public Long getAmount() {
		return amount;
	}


	public void setAmount(Long amount) {
		this.amount = amount;
	}


	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public LocalDate getDateDue() {
		return dateDue;
	}



	public void setDateDue(LocalDate dateDue) {
		this.dateDue = dateDue;
	}

	
}
