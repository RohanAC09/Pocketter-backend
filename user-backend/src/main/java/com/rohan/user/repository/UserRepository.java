package com.rohan.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rohan.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public Optional<User> findByEmail(String email);
	public Optional<User> findByUserId(Long userId);

	public void deleteByEmail(String email);
	public Long findUserIdByUsername(String username);

}
