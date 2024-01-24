package com.baha.bankapp.exception;

public class AccountNotActiveException extends RuntimeException {
	
	public AccountNotActiveException(String accNo) {
		super("Account is not active Account Number : " + accNo);
	}
	
}