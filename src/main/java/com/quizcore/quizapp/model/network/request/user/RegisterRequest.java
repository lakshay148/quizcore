package com.quizcore.quizapp.model.network.request.user;

import com.quizcore.quizapp.model.other.Validity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterRequest {
	
	String name;
	@NotEmpty  @Email(message = "Email should be valid")
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

	public Validity validate(RegisterRequest request){
		Validity validity = validatePhone(request.getPhone());
		if(!validity.isValid()){
			return validity;
		}
		Validity validity1 = validateEmail(request.getEmail());
		if(!validity1.isValid()){
			return validity1;
		}
		return new Validity(true, "valid");
	}

	public Validity validatePhone(String phone) {
		if(phone == null || phone.length() < 10 || phone.length() > 10){
			return new Validity(false, "invalid Phone");
		}
		return new Validity(true, "valid");
	}

	public Validity validateEmail(String email)
	{
		final String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.matches()){
			return new Validity(true, "valid");
		} else {
			return new Validity(false, "invalid email");
		}
	}

	public Validity checkNonNull(RegisterRequest request){
		return new Validity(true,"valid");
	}
}
