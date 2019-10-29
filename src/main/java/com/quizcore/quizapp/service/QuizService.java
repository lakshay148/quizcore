package com.quizcore.quizapp.service;

import java.util.*;
import java.util.stream.Collectors;

import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.repository.QuestionRepository;
import com.quizcore.quizapp.model.repository.QuizRespository;
import com.quizcore.quizapp.service.base.IQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizcore.quizapp.model.entity.Quiz;

@Service
public class QuizService implements IQuizService {

	@Autowired
	QuizRespository quizRespository;

	@Autowired
	QuestionRepository questionRepository;

	@Override
	public UUID addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz getQuiz(UUID quizId) {
		Optional<Quiz> quizOptional = quizRespository.findById(quizId);
		if(quizOptional.isPresent()){
			return quizOptional.get();
		} else {
			return null;
		}
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

	public List<Question> getQuestions(UUID quizId){
		Quiz savedQuiz = getQuiz(quizId);
		if(savedQuiz != null){
			String questionIds = savedQuiz.getQuestions();
			String[] questionsArray = questionIds.split(",");
			List<String> questionIdsString = Arrays.asList(questionsArray);
//			ArrayList<UUID> questionUUIDs =  Arrays.asList(questionsArray);
			ArrayList<UUID> questionUUIDs = (ArrayList<UUID>) questionIdsString.stream().map(id -> UUID.fromString(id)).collect(Collectors.toList());
			ArrayList<Question> questions = (ArrayList<Question>) questionRepository.findAllById(questionUUIDs);
			return questions;
		} else {
			return null;
		}
	}

}
