package com.baha.bankapp.common;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

	@CreatedBy
	@Column(name = "created_by", length = 20, nullable = false)
	protected String createdBy;
	
	@CreatedDate
	@Column(name = "created_dt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected LocalDateTime createdDate;
	
	@LastModifiedBy
	@Column(name = "updated_by", length = 20, nullable = false)
	protected String updatedBy;
	
	@LastModifiedDate
	@Column(name = "updated_dt", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected LocalDateTime updatedDate;
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
