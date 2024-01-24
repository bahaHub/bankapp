package com.baha.bankapp.exception;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(String id) {
		super("Could not find the customer id " + id);
	}
	
}
