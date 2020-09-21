package com.josan.accounting.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.josan.accounting.entity.Account;
import com.josan.accounting.service.IAccountsService;

@RestController
@RequestMapping("/home")
public class AccountsController {
	
	@Autowired
	private IAccountsService accounts;
	
	@GetMapping("/accounts")
	public List<Account> getAccounts(){
		return accounts.findAll();
	}
	
	@GetMapping("/accounts/{id}")
	public Optional<Account> getAccounts(@PathVariable("id") int idAccount){
		return accounts.search(idAccount);
	}
	
	
	@PostMapping("/accounts")
	public Account save(@RequestBody Account account) {
		
		if(account.isTreasury() == false) {
			if(account.getBalance() < 0) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non treasury accounts can not have negative balance");
			}
		}
		
		accounts.save(account);
		return account;
	}

}
