package com.quizcore.quizapp.model.network.request.user;

public class RegisterRequest {
	
	String name;
	String email;
	String password;
	String phone;
	String secret;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "AddUserRequest [name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ "]";
	}
	
	



}
