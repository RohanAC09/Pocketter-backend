package com.rohan.authentication_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rohan.authentication_service.apiclient.ApiClientService;
import com.rohan.authentication_service.dto.request.AuthenticationRequest;
import com.rohan.authentication_service.dto.response.AuthResponse;
import com.rohan.authentication_service.entity.Account;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationService {

	@Autowired
	JwtService jwtService;
	
	@Autowired
    PasswordEncoder encoder;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ApiClientService apiClientService;
	
	@Value("${user.backend.createUser.api}")
	private String userBackendCreateUser;
	
	public AuthResponse register(AuthenticationRequest request) {
        if (accountService.isAccountExists(request.email())) {
        	log.error("Error account already exists: {}", request.email());
            return new AuthResponse(new String("User already exists"),null);
        }

        Account newAccount = accountService.createAccount(request.email(), request.password());
        log.info("account {} has been created", newAccount.getAccountId());
        
        try {
			String jwt=jwtService.generateJwt(newAccount);
			String userResponse = apiClientService.callWithPathVariable(userBackendCreateUser, jwt, newAccount.getEmail());
			log.info("userBackendCreateUser call: {}", userResponse);
		} catch (Exception e) {
			log.error("userBackendCreateUser call: {}", e.getMessage());
			accountService.deleteAccountByEmail(newAccount.getEmail());
			return new AuthResponse("Registration failed. Message: " + e.getMessage() , null);
		}
        
        String successfulregistration="User registration successful. Please login.";
        return new AuthResponse(successfulregistration, null);
    }

	public AuthResponse login(@Valid AuthenticationRequest request) {
		
		Account userAccount = accountService.getAccountByEmail(request.email());

        if (!encoder.matches(request.password(), userAccount.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        
        String token = jwtService.generateJwt(userAccount);
        return new AuthResponse(new String("Success"), token);
	}

	public AuthResponse isTokenValid(String jwt) {
		if(jwt==null || jwt.isBlank() || jwt.isEmpty()) {
			log.error("401 Unauthorized");
			return new AuthResponse(new String("401 Unauthorized"), null);
		}
		
		try {
			String email=jwtService.getJwtUsername(jwt);
			if(email==null) {
				throw new RuntimeException("Invalid Token");
			}
			
			Account userAccount = accountService.getAccountByEmail(email);
			
			String message = jwtService.isJwtValid(jwt, userAccount) ? "Token validation successful" : "Invalid Token";
			
			return new AuthResponse(message, jwt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new AuthResponse(e.getMessage(), null);
		}
	}
}
