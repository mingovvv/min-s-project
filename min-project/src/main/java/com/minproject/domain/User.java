package com.minproject.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	
	@Id
	@JsonProperty
	private String idx;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private long rowNum;
	

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

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public Long getRowNum() {
		return rowNum;
	}
	
	public void setRowNum(Long rowNum) {
		this.rowNum = rowNum;
	}
	
	public void setUnit() {
		idx = UUID.randomUUID().toString();
	}
	
	@Override
	public String toString() {
		return "User [idx=" + idx + ", rowNum=" + rowNum + ", userId=" + userId + ", userEmail=" + userEmail
				+ ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}

	// 로그인 password 확인
	public boolean matchPassword(String writtenPassword) {
		return writtenPassword.equals(userPassword);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idx == null) ? 0 : idx.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (idx == null) {
			if (other.idx != null)
				return false;
		} else if (!idx.equals(other.idx))
			return false;
		return true;
	}



}
