package com.rohan.user.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohan.user.dto.response.UserResponse;
import com.rohan.user.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserProfileService {
	
	@Autowired
	UserService userService;

	public UserResponse createuser(String email) {
		if (userService.isUserExists(email)) {
        	log.error("Error user already exists: {}", email);
            return new UserResponse(new String("User already exists"), new Timestamp(System.currentTimeMillis()));
        }

        User newUser = userService.createAccount(email);
        log.info("New user {} has been created", email);
        
        String responsemessage="User creation successful";
        return new UserResponse(responsemessage, new Timestamp(System.currentTimeMillis()));
	}

}
