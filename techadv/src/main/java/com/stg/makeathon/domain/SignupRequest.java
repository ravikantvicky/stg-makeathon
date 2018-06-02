package com.stg.makeathon.domain;

import java.util.ArrayList;
import java.util.List;

public class SignupRequest {
	private String name, email, mobile, password;
	private List<String> prefrences;

	public SignupRequest() {
		prefrences = new ArrayList<>();
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getPrefrences() {
		return prefrences;
	}

	public void setPrefrences(List<String> prefrences) {
		this.prefrences = prefrences;
	}
}
