package com.minproject.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question {

	@Id
	@JsonProperty
	private String idx;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private long rowNum;


	@JsonProperty
	private String title;

	@JsonProperty
	@Lob
	private String contents;

	@JsonProperty
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonManagedReference
	@JsonProperty
	private User user;

	@JsonProperty
	@JsonBackReference
	@OneToMany(mappedBy = "question")
	@OrderBy("date ASC")
	private List<Answer> answers;
	
	@JsonProperty
	private long countOfAnswer;

	public String getTitle() {
		return title;
	}

	public String getContents() {
		return contents;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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

	public String getFormattedDate() {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public boolean isSameUser(User loginUser) {
		return user.equals(loginUser);
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
		Question other = (Question) obj;
		if (idx == null) {
			if (other.idx != null)
				return false;
		} else if (!idx.equals(other.idx))
			return false;
		return true;
	}

	public void setUnit(User user) {
		this.user = user;
		idx = UUID.randomUUID().toString();
		date = LocalDateTime.now();
	}

	public void addAnswer() {
		countOfAnswer++;
	}

	public void deleteAnswer() {
		countOfAnswer--;
	}

}
