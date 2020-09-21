package com.josan.accounting.service;

import java.util.List;

import com.josan.accounting.entity.Account;

public interface IAccountsService {
	
	public List<Account> findAll();
	
	void save(Account account);
	
	void delete(int idAccount);

}
