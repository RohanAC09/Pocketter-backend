package com.rohan.authentication_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String email;
    private String password;
    
    public String getUsername() {
    	return email;
    }
    
    public String setUsername(String username) {
    	return this.email=username;
    }


	public Long getAccountId() {
		return accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
