package com.minproject.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

	@Id
	@JsonProperty
	private String idx;

	@JsonProperty
	private long rowNum;
	
	@CreatedDate
	@JsonProperty
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	@JsonProperty
	private LocalDateTime modifiedDate;

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public void setUnit() {
		idx = UUID.randomUUID().toString();
	}
	
	public String getFormattedCreatedDate() {
		return getFormattedDate(createdDate,"yyyy-MM-dd HH:mm:ss");
	}
	
	public String getFormattedModifiedDate() {
		return getFormattedDate(modifiedDate,"yyyy-MM-dd HH:mm:ss");
	}
	
	private String getFormattedDate(LocalDateTime date, String format) {
		if (date == null) {
			return "";
		}
		return date.format(DateTimeFormatter.ofPattern(format));
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
		AbstractEntity other = (AbstractEntity) obj;
		if (idx == null) {
			if (other.idx != null)
				return false;
		} else if (!idx.equals(other.idx))
			return false;
		return true;
	}

}
