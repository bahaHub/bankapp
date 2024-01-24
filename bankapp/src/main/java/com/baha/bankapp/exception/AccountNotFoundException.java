package com.baha.bankapp.exception;

public class AccountNotFoundException extends RuntimeException {
	
	public AccountNotFoundException(String accNo) {
		super("Could not find the Account with Number : " + accNo);
	}
	
}