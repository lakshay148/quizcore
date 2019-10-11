package com.quizcore.quizapp.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.network.request.AddQuestionRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.question.AddQuestionResponse;
import com.quizcore.quizapp.model.network.response.question.GetQuestionResponse;
import com.quizcore.quizapp.model.network.response.question.UploadQuestionResponse;
import com.quizcore.quizapp.service.QuestionService;

@RestController
@RequestMapping("quizcore/api/v1/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("/healthcheck")
	public BaseResponse<Object> checkHealth() {
		BaseResponse<Object> response = new BaseResponse<>();
		response.message = "It works awesone";
		response.status = 200;
		return response;
	}
	
	@PostMapping("/add")
	public BaseResponse<AddQuestionResponse> addQuestion(@RequestBody AddQuestionRequest request){
		Question question = new Question(request.getStatement(), request.getType(),request.getLevel(), request.getSubject());
		UUID questionId = questionService.addQuestion(question);
		BaseResponse<AddQuestionResponse> response = new BaseResponse<>();
		response.message = "question added successfully";
		response.status = 200;
		AddQuestionResponse addQuestion = new AddQuestionResponse();
		addQuestion.questionId = questionId;
		response.data = addQuestion;
		return response;
	}
	
	@PostMapping("/upload")
	public BaseResponse<UploadQuestionResponse> uploadQuestion(){
		BaseResponse<UploadQuestionResponse> response = new BaseResponse<>();
		response.message = "It works awesone";
		response.status = 200;
		return response;
	}
	
	@GetMapping("/{questionId}")
	public BaseResponse<GetQuestionResponse> getQuestion(@PathVariable("questionId") String questionId){
		BaseResponse<GetQuestionResponse> response = new BaseResponse<>();
		response.message = "It works awesone";
		response.status = 200;
		return response;
	}
	
}
