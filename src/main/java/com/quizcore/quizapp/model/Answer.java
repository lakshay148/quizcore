package com.quizcore.quizapp.model;

import java.util.UUID;

public class Answer {

	UUID questionId;
	public String text;
	public UUID[] options;

	public UUID getQuestionId() {
		return questionId;
	}

	public void setQuestionId(UUID questionId) {
		this.questionId = questionId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UUID[] getOptions() {
		return options;
	}

	public void setOptions(UUID[] options) {
		this.options = options;
	}
}
