package com.ethereal.learning.data;

public class UserProfile {
	private String name;
	private String email;
	private String authorities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "UserProfile [name=" + name + ", email=" + email + ", authories=" + authorities + "]";
	}
}
