package com.minproject.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity{

	@JsonProperty
	private String title;

	@JsonProperty
	@Lob
	private String contents;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonManagedReference
	@JsonProperty
	private User user;

	@JsonProperty
	@JsonBackReference
	@OneToMany(mappedBy = "question")
	@OrderBy("createdDate ASC")
	private List<Answer> answers;
	
	@JsonProperty
	private long countOfAnswer;

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void update(String updatedTitle, String updatedContents) {
		title = updatedTitle;
		contents = updatedContents;
	}

	public boolean isSameUser(User loginUser) {
		return user.equals(loginUser);
	}

	public void setUnit(User user) {
		super.setUnit();
		this.user = user;
	}

	public void addAnswer() {
		countOfAnswer++;
	}

	public void deleteAnswer() {
		countOfAnswer--;
	}

}
