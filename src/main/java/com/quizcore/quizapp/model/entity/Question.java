package com.quizcore.quizapp.model.entity;

import java.util.List;
import java.util.UUID;

import com.quizcore.quizapp.model.Answer;
import com.quizcore.quizapp.model.other.Option;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	@Type(type="uuid-char")
	public UUID id;

	@Column
	public String statement;

	@Column
	public String type;

	@Column
	public int level;

	@Column
	public String subject;

	@Column
	public String options;

	@Column
	public String answer;

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "Question{" +
				"id=" + id +
				", statement='" + statement + '\'' +
				", type='" + type + '\'' +
				", level=" + level +
				", subject='" + subject + '\'' +
				", options=" + options +
				", answer=" + answer +
				'}';
	}
}
