package com.josan.accounting.service;

import java.util.List;
import java.util.Optional;

import com.josan.accounting.entity.Account;

public interface IAccountsService {
	
	public List<Account> findAll();
	
	void save(Account account);
	
	void delete(int idAccount);
	
	public Optional<Account> search(int id);

}
