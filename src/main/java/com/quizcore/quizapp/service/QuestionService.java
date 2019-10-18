package com.quizcore.quizapp.service;

import java.util.List;
import java.util.UUID;

import com.quizcore.quizapp.service.base.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.repository.QuestionRepository;

@Service
public class QuestionService implements IQuestionService {


//	@Autowired
//	QuestionRepository questionRepository;
	
	@Override
	public UUID addQuestion(Question question) {
		// TODO Auto-generated method stub
		System.out.println("question added " + question.id);
		return question.id;
	}

	@Override
	public List<Question> getQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

}
