package org.caringbridge.ui.audits.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "site_profile")
public class SiteProfile {
	public enum Role { Visitor, Organizer }
	private Integer siteId;
	private Integer userId;
	private Role role;
	public Integer getSiteId() {
		return siteId;
	}
	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
