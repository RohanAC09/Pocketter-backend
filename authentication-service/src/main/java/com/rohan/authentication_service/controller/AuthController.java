package com.rohan.authentication_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohan.authentication_service.dto.request.AuthenticationRequest;
import com.rohan.authentication_service.dto.request.TokenValidationRequest;
import com.rohan.authentication_service.dto.response.AuthResponse;
import com.rohan.authentication_service.service.AuthenticationService;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	AuthenticationService authenticationService;
	
	@PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthenticationRequest request) {
		AuthResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
    	AuthResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate/jwt")
    public ResponseEntity<AuthResponse> isTokenValid(@RequestBody TokenValidationRequest request) {
        return ResponseEntity.ok(authenticationService.isTokenValid(request.getToken()));
    }
}
