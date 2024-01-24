package com.baha.bankapp.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baha.bankapp.common.CommonUtils;
import com.baha.bankapp.dto.AccountDto;
import com.baha.bankapp.exception.AccountInsufficientBalanceException;
import com.baha.bankapp.exception.AccountNotActiveException;
import com.baha.bankapp.exception.AccountNotFoundException;
import com.baha.bankapp.model.Account;
import com.baha.bankapp.repository.AccountRepository;
import com.baha.bankapp.repository.CustomerRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class AccountController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/account/create/{custNo}")
	ResponseEntity<Account> newAccount(@RequestBody Account newAccount, @PathVariable String custNo) {
		newAccount.setCustNo(custNo);
		newAccount.setAccNo(CommonUtils.generateAccountNumber());
		newAccount.setAccBal(BigDecimal.ZERO);
		newAccount.setAccStatus(CommonUtils.ACCOUNT_ACTIVE);
		newAccount.setCreatedBy(CommonUtils.STR_SYSTEM);
		newAccount.setCreatedDate(LocalDateTime.now());
		newAccount.setUpdatedBy(CommonUtils.STR_SYSTEM);
		newAccount.setUpdatedDate(LocalDateTime.now());
		
		Account entity = accountRepository.save(newAccount);
		
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping("/account/list/{custNo}")
	ResponseEntity<List> getCustomerAccountList(@PathVariable String custNo) {
		List<Account> entity = accountRepository.findByCustNo(custNo);
		System.out.println("custNo:" + custNo);
		
		return ResponseEntity.ok().body(entity);
	}
	
	@PutMapping("/account/deposit/{accNo}")
	ResponseEntity<Account> depositAmount(@RequestBody AccountDto accountDto, @PathVariable String accNo) {
		Optional<Account> result = accountRepository.findById(accNo);
		if (result.isEmpty()) {
			throw new AccountNotFoundException(accNo);
		} else if (result.get().getAccStatus() == 0) {
			throw new AccountNotActiveException(accNo);
		}

	    Account entity = result.get();
	    BigDecimal newAccBall = entity.getAccBal().add(accountDto.getDepoAmt());
	    entity.setAccBal(newAccBall);
	    entity.setUpdatedBy(CommonUtils.STR_SYSTEM);
    	entity.setUpdatedDate(LocalDateTime.now());
	    entity = accountRepository.saveAndFlush(entity);
	    
	    return ResponseEntity.ok().body(entity);
	}
	
	@PutMapping("/account/withdraw/{accNo}")
	ResponseEntity<Account> withdrawAmount(@RequestBody AccountDto accountDto, @PathVariable String accNo) {
		Optional<Account> result = accountRepository.findById(accNo);
		if (result.isEmpty()) {
			throw new AccountNotFoundException(accNo);
		} else if (result.get().getAccStatus() == 0) {
			throw new AccountNotActiveException(accNo);
		}

	    Account entity = result.get();
	    
	    if (accountDto.getWdAmt().compareTo(entity.getAccBal()) > 0) {
	    	throw new AccountInsufficientBalanceException(accNo);
	    }
	    
	    BigDecimal newAccBall = entity.getAccBal().subtract(accountDto.getWdAmt());
	    entity.setAccBal(newAccBall);
	    entity.setUpdatedBy(CommonUtils.STR_SYSTEM);
    	entity.setUpdatedDate(LocalDateTime.now());
	    entity = accountRepository.saveAndFlush(entity);
	    
	    return ResponseEntity.ok().body(entity);
	}
	
	@PutMapping("/account/deactivate/{accNo}")
	ResponseEntity<Account> CloseAccount(@RequestBody AccountDto accountDto, @PathVariable String accNo) {
		Optional<Account> result = accountRepository.findById(accNo);
		if (result.isEmpty()) {
			throw new AccountNotFoundException(accNo);
		} else if (result.get().getAccStatus() == 0) {
			throw new AccountNotActiveException(accNo);
		}

	    Account entity = result.get();
	    
	    if (entity.getAccBal().compareTo(BigDecimal.ZERO) > 0.0) {
	    	throw new AccountInsufficientBalanceException(accNo);
	    }
	    
    	entity.setAccStatus(CommonUtils.ACCOUNT_INACTIVE);
    	entity.setUpdatedBy(CommonUtils.STR_SYSTEM);
    	entity.setUpdatedDate(LocalDateTime.now());
	    entity = accountRepository.saveAndFlush(entity);
	    
	    return ResponseEntity.ok().body(entity);
	}
	
}
