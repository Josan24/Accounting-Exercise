package com.josan.accounting.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josan.accounting.entity.Account;
import com.josan.accounting.repository.AccountsRepository;
import com.josan.accounting.service.IAccountsService;

@Service
public class AccountsService implements IAccountsService {
	
	@Autowired
	private AccountsRepository accounts;

	
	public List<Account> findAll() {
		
		return (List<Account>) accounts.findAll();
	}

	
	public void save(Account account) {
		accounts.save(account);

	}

	
	public void delete(int idAccount) {
		accounts.deleteById(idAccount);

	}


	
	public Optional<Account> search(int id) {
		
		return (Optional<Account>) accounts.findById(id);
	}

}
