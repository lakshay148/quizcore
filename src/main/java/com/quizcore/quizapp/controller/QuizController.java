package com.quizcore.quizapp.controller;

import java.util.UUID;

import com.quizcore.quizapp.model.network.request.quiz.AddQuizRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.network.request.question.AddQuestionRequest;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
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
	public SuccessResponse<Object> checkHealth() {
		SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
		return response;
	}
	
	@PostMapping("/add")
	public SuccessResponse<AddQuizResponse> addQuiz(@RequestBody AddQuizRequest request){
		Quiz quiz = new Quiz();
		UUID quizId = quizService.addQuiz(quiz);
		SuccessResponse<AddQuizResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}
	
	@PostMapping("/upload")
	public SuccessResponse<UploadQuizResponse> uploadQuiz(){
		SuccessResponse<UploadQuizResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}
	
	@GetMapping("/{quizId}")
	public SuccessResponse<GetQuizResponse> getQuiz(@PathVariable("quizId") String quizId){
		SuccessResponse<GetQuizResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}

	@GetMapping("{quizId}/question")
	public SuccessResponse<GetQuizResponse> getQuizQuestions(@PathVariable("quizId") String quizId){
		SuccessResponse<GetQuizResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}

	@PostMapping("{quizId}/start")
	public SuccessResponse<UploadQuizResponse> startQuiz(){
		SuccessResponse<UploadQuizResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}

	@PostMapping("{quizId}/submit")
	public SuccessResponse<UploadQuizResponse> submitQuiz(){
		SuccessResponse<UploadQuizResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}

	@GetMapping("{quizId}/result")
	public SuccessResponse<GetQuizResponse> getQuizResult(@PathVariable("quizId") String quizId){
		SuccessResponse<GetQuizResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}
}
