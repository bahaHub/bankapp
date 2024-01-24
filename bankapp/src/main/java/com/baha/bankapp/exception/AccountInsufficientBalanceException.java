package com.baha.bankapp.exception;

public class AccountInsufficientBalanceException extends RuntimeException {
	
	public AccountInsufficientBalanceException(String accNo) {
		super("Account Balance is insufficent Account Number : " + accNo);
	}
	
}