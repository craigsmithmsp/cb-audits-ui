package org.caringbridge.ui.audits.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Site {
    @Id
	private int _id;
	private String isDeleted;
	private String name;
	private Integer primaryOrganizerId;
	private LocalDateTime createdAt;
	private Date updatedAt;
	
	// Getters and Setters
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrimaryOrganizerId() {
		return primaryOrganizerId;
	}
	public void setPrimaryOrganizerId(Integer primaryOrganizerId) {
		this.primaryOrganizerId = primaryOrganizerId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
