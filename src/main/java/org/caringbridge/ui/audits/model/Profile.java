package org.caringbridge.ui.audits.model;

import org.springframework.data.annotation.Id;

public class Profile {
	@Id
	private int _id;
	private Email email;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}

	public class Email {
		private String address;

		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	}
}
