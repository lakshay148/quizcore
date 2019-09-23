package com.quizcore.quizapp.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.repository.QuestionRepository;

@Service
public class QuestionService implements IQuestionService{


	@Autowired
	QuestionRepository questionRepository;
	
	@Override
	public UUID addQuestion(Question question) {
		// TODO Auto-generated method stub
		Question addedQuestion = questionRepository.save(question);
		System.out.println("question added " + question.id);
		return addedQuestion.id;
	}

	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

}
