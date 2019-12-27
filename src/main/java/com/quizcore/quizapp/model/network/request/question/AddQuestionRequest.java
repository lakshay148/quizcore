package com.quizcore.quizapp.model.network.request.question;

public class AddQuestionRequest {

	String statement;
	String type;
	int level;
	String subject;
	
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
		return "AddQuestionRequest [statement=" + statement + ", type=" + type + ", level=" + level + ", subject="
				+ subject + "]";
	}
	
}
