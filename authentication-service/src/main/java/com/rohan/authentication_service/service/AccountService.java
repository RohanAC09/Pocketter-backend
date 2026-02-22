package com.rohan.authentication_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rohan.authentication_service.entity.Account;
import com.rohan.authentication_service.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Account createAccount(String email, String password) {
		return accountRepository.saveAndFlush(
                Account.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build() );
	}

	public boolean isAccountExists(String email) {
		return accountRepository.findByEmail(email)
				.isPresent();
	}
	
	public Account getAccountByEmail(String email) {
		Account userAccount = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
		return userAccount;
	}
	
	public void deleteAccountByEmail(String email) {
		accountRepository.deleteByEmail(email);
	}

}
