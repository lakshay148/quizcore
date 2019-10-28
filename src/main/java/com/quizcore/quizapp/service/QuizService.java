package com.quizcore.quizapp.service;

import java.util.UUID;

import com.quizcore.quizapp.model.repository.QuizRespository;
import com.quizcore.quizapp.service.base.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizcore.quizapp.model.entity.Quiz;

@Service
public class QuizService implements IQuizService {

	@Autowired
	QuizRespository quizRespository;

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
		Quiz savedQuiz = quizRespository.save(quiz);
		return savedQuiz.id;
	}

	@Override
	public UUID submitQuiz(UUID quizid, UUID userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
