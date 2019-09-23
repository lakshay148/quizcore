package com.quizcore.quizapp.model.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Question {
	
	public Question(){
		
	}
	
	public Question(String statement, String type, int level, String subject){
		this.statement = statement;
		this.type = type;
		this.level = level;
		this.subject = subject;
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "id", updatable = false, nullable = false)
	public UUID id;
	
	@Column(name = "statement")
	public String statement;
	
	@Column(name= "type")
	public String type;
	
	@Column
	public int level;
	
	@Column
	public String subject;
}
