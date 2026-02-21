package com.rohan.user.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rohan.user.entity.User;
import com.rohan.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${user.default_bio}")
	private String defaultBio;

	public boolean isUserExists(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	public void createUser(String email) {
		userRepository.saveAndFlush(
                User.builder()
                .email(email)
                .username(email)
                .fullName(email)
                .bio(defaultBio)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build() );
	}

	public void saveUser(User user) {
		userRepository.saveAndFlush(user);
	}

	public Optional<User> findUserByUserId(Long userId) {
		return userRepository.findByUserId(userId);
	}

	public Long findUserIdByUsername(String username) {
		return userRepository.findUserIdByUsername(username);
	}

	public void deleteUserByEmail(String email) {
		userRepository.deleteByEmail(email);
	}

}
