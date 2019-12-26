package com.minproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	
	@Id
	private String idx;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rowNum;
	private String userId;
	private String title;
	private String contents;
	
	public Question() {
		// TODO Auto-generated constructor stub
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public long getRowNum() {
		return rowNum;
	}

	public void setRowNum(long rowNum) {
		this.rowNum = rowNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "Question [idx=" + idx + ", rowNum=" + rowNum + ", userId=" + userId + ", title=" + title + ", contents="
				+ contents + "]";
	}
}
