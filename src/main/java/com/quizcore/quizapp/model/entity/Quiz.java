package com.quizcore.quizapp.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Quiz {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	@Type(type="uuid-char")
	public UUID id;

	@Column
	@Type(type="uuid-char")
	UUID partnerId;

	@Column
	String title;

	@Column
	String description;

	@Column
	String instructions;

	@Column
	int level;

	@Column
	String subject;

	@Column
	String category;

	@Column
	String questions;

	@Column
	int duration;

	@Column
	double payment;

	@Column
	String type;

	@Column
	int correctMarks;

	@Column
	int incorrectMarks;

	@Column
	int passingCriteria;

	@Column(columnDefinition = "TINYINT(1) DEFAULT 0")
	int videoRequired;

	@Column(columnDefinition = "TINYINT(1) DEFAULT 0")
	int paymentRequired;

	@CreationTimestamp
	Date createdAt;

	@UpdateTimestamp
	Date updatedAt;



	public int getPassingCriteria() {
		return passingCriteria;
	}

	public void setPassingCriteria(int passingCriteria) {
		this.passingCriteria = passingCriteria;
	}

	public Quiz() {
	}

	public Quiz(String title, String description, String instructions, int level, String subject, String category, int duration, double payment, String type, int correctMarks, int incorrectMarks) {
		this.title = title;
		this.description = description;
		this.instructions = instructions;
		this.level = level;
		this.subject = subject;
		this.category = category;
		this.duration = duration;
		this.payment = payment;
		this.type = type;
		this.correctMarks = correctMarks;
		this.incorrectMarks = incorrectMarks;
	}

	public int getCorrectMarks() {
		return correctMarks;
	}

	public void setCorrectMarks(int correctMarks) {
		this.correctMarks = correctMarks;
	}

	public int getIncorrectMarks() {
		return incorrectMarks;
	}

	public void setIncorrectMarks(int incorrectMarks) {
		this.incorrectMarks = incorrectMarks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(UUID partnerId) {
		this.partnerId = partnerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}
}
