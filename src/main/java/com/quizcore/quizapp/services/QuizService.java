package com.quizcore.quizapp.services;

import java.util.UUID;

import com.quizcore.quizapp.services.base.IQuizService;
import org.springframework.stereotype.Service;

import com.quizcore.quizapp.model.entity.Quiz;

@Service
public class QuizService implements IQuizService {

	@Override
	public UUID addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz getQuiz(UUID quizId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID uploadQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UUID submitQuiz(UUID quizid, UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
