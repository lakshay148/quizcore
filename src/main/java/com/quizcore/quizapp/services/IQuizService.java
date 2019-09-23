package com.quizcore.quizapp.services;

import java.util.UUID;

import com.quizcore.quizapp.model.entity.Quiz;

public interface IQuizService {
	UUID addQuiz(Quiz quiz);
	Quiz getQuiz(UUID quizId);
	UUID uploadQuiz(Quiz quiz);
	UUID submitQuiz(UUID quizid, UUID userId);
}
