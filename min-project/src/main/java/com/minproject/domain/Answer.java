package com.minproject.domain;


import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer extends AbstractEntity{
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonManagedReference
	@JsonProperty
	private User user;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
	@JsonManagedReference
	@JsonProperty
	private Question question;

	@Lob
	@JsonProperty
	private String contents;

	public Answer() {
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setUnit(User user, Question question) {
		super.setUnit();
		this.user = user;
		this.question = question;
	}


	public boolean isSameUser(User loginUser) {
		return user.equals(loginUser);
	}

}
