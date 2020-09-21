package com.josan.accounting.repository;

import org.springframework.data.repository.CrudRepository;

import com.josan.accounting.entity.Account;

public interface AccountsRepository extends CrudRepository<Account, Integer> {

}
