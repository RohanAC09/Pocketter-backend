package com.rohan.user.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rohan.user.dto.response.UserResponse;
import com.rohan.user.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserProfileService {
	
	@Autowired
	private UserService userService;

	public UserResponse createUser(String email) {
		
		if (userService.isUserExists(email)) {
        	log.info("CreateUser Error: user already exists: {}", email);
            return new UserResponse(new String("User already exists"), new Timestamp(System.currentTimeMillis()));
        }

        userService.createUser(email);
        log.info("New user {} has been created", email);
        
        String responsemessage="User creation successful";
        return new UserResponse(responsemessage, new Timestamp(System.currentTimeMillis()));
	}

	public String viewProfile(Long userId) {
		Optional<User> optionalUser=userService.findUserByUserId(userId);
		
		if ( !optionalUser.isPresent()) {
        	log.info("viewProfile Error: user does not exists: {}", userId);
            return new UserResponse(new String("User does not exists"), new Timestamp(System.currentTimeMillis())).toString();
        }
        
        return optionalUser.get().toString();
	}

	public UserResponse deleteUserByEmail(String email) {
		
		if ( !userService.isUserExists(email)) {
        	log.info("deleteUserByEmail Error: user does not exists: {}", email);
            return new UserResponse(new String("User does not exists."), new Timestamp(System.currentTimeMillis()));
        }
		
		userService.deleteUserByEmail(email);
        String responsemessage="Deletion operation successful. Deleted User: " + email;
        log.info(responsemessage);
        
        return new UserResponse(responsemessage, new Timestamp(System.currentTimeMillis()));
	}

}
