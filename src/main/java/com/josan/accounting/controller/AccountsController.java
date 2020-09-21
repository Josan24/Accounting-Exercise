package com.josan.accounting.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/accounts")
	public Account update(@RequestBody Account account) {
		// The field Treasury can not be modified
		account.setTreasury(account.isTreasury());
		accounts.save(account);
		
		return account;		
	}
	
	@DeleteMapping("/accounts/{id}")
	public String delete(@PathVariable("id") int idAccount) {
		accounts.delete(idAccount);
		return "Account Deleted";
	}
	
	@PutMapping("/accounts/{id1}/{id2}/{money}")
	public String transfer(@PathVariable("id1") int id1, @PathVariable("id2") int id2 ,@PathVariable("money") int money) {
		Optional<Account> acc1 = accounts.search(id1);
		Optional<Account> acc2 = accounts.search(id2);
		
		if (acc1.get().isTreasury() == false) {
			if (acc1.get().getBalance() < money) {
				// We must block the transfer
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non treasury accounts can not have negative balance");
			}
		}
		acc1.get().setBalance(acc1.get().getBalance() - money);
		acc2.get().setBalance(acc2.get().getBalance() + money);
		
		accounts.save(acc1.get());
		accounts.save(acc2.get());
		
		throw new ResponseStatusException(HttpStatus.ACCEPTED, "Transfer accepted");
	}

}
