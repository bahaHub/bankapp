package com.baha.bankapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baha.bankapp.common.CommonUtils;
import com.baha.bankapp.exception.CustomerNotFoundException;
import com.baha.bankapp.model.Customer;
import com.baha.bankapp.repository.CustomerRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping("/register")
	ResponseEntity<Customer> newCust(@RequestBody Customer newCust) {
		
		newCust.setCreatedBy(CommonUtils.STR_SYSTEM);
		newCust.setCreatedDate(LocalDateTime.now());
		newCust.setUpdatedBy(CommonUtils.STR_SYSTEM);
		newCust.setUpdatedDate(LocalDateTime.now());
		
		return ResponseEntity.ok().body(customerRepository.save(newCust));
	}
	
	@GetMapping("/users")
	List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/user/{custNo}")
	List<Customer> getCustById(@PathVariable String custNo) {
		
		List<Customer> user = customerRepository.findByCustNo(custNo);
		
		if (user.isEmpty()) {
			throw new CustomerNotFoundException(custNo);
		}
		
		return user;
	}
}
