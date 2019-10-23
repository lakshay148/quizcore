package com.quizcore.quizapp.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {

	public User(){

	}

	public User(String name, String email, String password, String phone) {
		this.name = name;
		this.email = email;
		this.password=password;
		this.phone=phone;
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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


	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
			name = "UUID",
			strategy = "org.hibernate.id.UUIDGenerator"
			)
	@Column(name = "id", updatable = false, nullable = false)
	public UUID id;

	@Column(name = "name")
	public String name;

	@Column(name= "email")
	public String email;

	@Column(name = "password")
	public String password;

	@Column(name = "phone")
	public String phone;
}

