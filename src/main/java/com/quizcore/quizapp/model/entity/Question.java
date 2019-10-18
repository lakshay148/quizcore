package com.quizcore.quizapp.model.entity;

import java.util.List;
import java.util.UUID;

import com.quizcore.quizapp.model.Answer;
import com.quizcore.quizapp.model.other.Option;

public class Question {
	
	public Question(){
		
	}
	
	public Question(String statement, String type, int level, String subject){
		this.statement = statement;
		this.type = type;
		this.level = level;
		this.subject = subject;
	}

	public UUID id;
	
	public String statement;
	
	public String type;
	
	public int level;
	
	public String subject;

	public List<Option> options;

	public Answer answer;

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
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
