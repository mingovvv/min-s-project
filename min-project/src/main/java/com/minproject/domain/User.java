package com.minproject.domain;


import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity{
	
	@JsonProperty
	@Column(nullable = false)
	private String userId;

	@JsonProperty
	private String userEmail;

	@JsonProperty
	private String userName;

	@JsonIgnore
	private String userPassword;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	// 로그인 password 확인
	public boolean matchPassword(String writtenPassword) {
		return writtenPassword.equals(userPassword);
	}

}

