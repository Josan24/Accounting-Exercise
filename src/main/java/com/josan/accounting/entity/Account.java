package com.josan.accounting.entity;

import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String Name;
	private Currency Currency;
	private double Balance;
	private boolean Treasury;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Currency getCurrency() {
		return Currency;
	}

	public void setCurrency(Currency currency) {
		Currency = currency;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		Balance = balance;
	}

	public boolean isTreasury() {
		return Treasury;
	}

	public void setTreasury(boolean treasury) {
		Treasury = treasury;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", Name=" + Name + ", Currency=" + Currency + ", Balance=" + Balance
				+ ", Treasury=" + Treasury + "]";
	}
	
	

}
