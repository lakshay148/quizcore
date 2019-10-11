package com.quizcore.quizapp.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.network.request.AddQuestionRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.quiz.AddQuizResponse;
import com.quizcore.quizapp.model.network.response.quiz.GetQuizResponse;
import com.quizcore.quizapp.model.network.response.quiz.UploadQuizResponse;
import com.quizcore.quizapp.service.QuizService;

@RestController
@RequestMapping("quizcore/api/v1/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@GetMapping("/healthcheck")
	public BaseResponse<Object> checkHealth() {
		BaseResponse<Object> response = new BaseResponse<>();
		response.message = "It works awesone";
		response.status = 200;
		return response;
	}
	
	@PostMapping("/add")
	public BaseResponse<AddQuizResponse> addQuiz(@RequestBody AddQuestionRequest request){
		Quiz quiz = new Quiz();
		UUID quizId = quizService.addQuiz(quiz);
		BaseResponse<AddQuizResponse> response = new BaseResponse<>();
		response.message = "quiz added successfully";
		response.status = 200;
		return response;
	}
	
	@PostMapping("/upload")
	public BaseResponse<UploadQuizResponse> uploadQuestion(){
		BaseResponse<UploadQuizResponse> response = new BaseResponse<>();
		response.message = "It works awesone";
		response.status = 200;
		return response;
	}
	
	@GetMapping("{quizId}")
	public BaseResponse<GetQuizResponse> getQuestion(@PathVariable("quizId") String quizId){
		BaseResponse<GetQuizResponse> response = new BaseResponse<>();
		response.message = "It works awesone";
		response.status = 200;
		return response;
	}
	
}
