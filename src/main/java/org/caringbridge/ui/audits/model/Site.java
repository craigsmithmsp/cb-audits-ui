package org.caringbridge.ui.audits.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public class Site {
    @Id
	private int _id;
	private String isDeleted;
	private String name;
	@Transient
	private int profileId;
	
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
	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
}
