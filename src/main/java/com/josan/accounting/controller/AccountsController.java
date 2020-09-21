package com.josan.accounting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josan.accounting.entity.Account;
import com.josan.accounting.service.IAccountsService;

@RestController
@RequestMapping("/home")
public class AccountsController {
	
	@Autowired
	private IAccountsService accounts;
	
	public List<Account> getAccounts(){
		return accounts.findAll();
	}

}
