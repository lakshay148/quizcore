package com.quizcore.quizapp.services;

import java.util.List;
import java.util.UUID;

import com.quizcore.quizapp.model.entity.Question;

public interface IQuestionService {
	
	UUID addQuestion(Question question);
	List<Question> getQuestions();
}
