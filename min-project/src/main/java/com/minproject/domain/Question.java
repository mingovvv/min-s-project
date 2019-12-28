package com.minproject.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
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

@Entity
public class Question {
	
	@Id
	private String idx;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rowNum;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User user;
	
	private String title;
	@Lob
	private String contents;
	private LocalDateTime date;
	
	@OneToMany(mappedBy = "question")
	@OrderBy("date ASC")
	private List<Answer> answers;
	
	public String getFormattedDate() {
		return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
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
	
	public User getUserId() {
		return user;
	}

	public void setUserId(User userId) {
		this.user = userId;
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

	public void update(String updatedTitle, String updatedContents) {
		title = updatedTitle;
		contents = updatedContents;
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
	
	@Override
	public String toString() {
		return "Question [idx=" + idx + ", rowNum=" + rowNum + ", user=" + user + ", title=" + title + ", contents="
				+ contents + ", date=" + date + ", answers=" + answers + "]";
	}


}
