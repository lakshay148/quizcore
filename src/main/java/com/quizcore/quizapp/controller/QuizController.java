package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.Answer;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.entity.Result;
import com.quizcore.quizapp.model.entity.UserActivityLog;
import com.quizcore.quizapp.model.network.request.quiz.AddQuizRequest;
import com.quizcore.quizapp.model.network.request.quiz.SubmitQuizRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.quiz.*;
import com.quizcore.quizapp.model.network.response.user.UserQuizResponse;
import com.quizcore.quizapp.model.other.QuestionDetail;
import com.quizcore.quizapp.model.other.UserQuizActions;
import com.quizcore.quizapp.model.other.UserQuizResult;
import com.quizcore.quizapp.service.QuizService;
import com.quizcore.quizapp.service.UserActivityService;
import com.quizcore.quizapp.util.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${base.endpoint}/api/v1/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	@Autowired
	UserActivityService userActivityService;

	@GetMapping("/healthcheck")
	public SuccessResponse<Object> checkHealth() {
		SuccessResponse<Object> response = new SuccessResponse<>("It works awesome");
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
	public BaseResponse<UploadQuizResponse> uploadQuiz(@RequestParam("file") MultipartFile quizFile, @RequestParam(value = "partnerId", required = true) String partnerId) throws IOException {
		SuccessResponse<UploadQuizResponse> response = new SuccessResponse<>("Quiz uploaded");
		UUID quizId = quizService.uploadQuiz(UUID.fromString(partnerId), quizFile);
		response.data = new UploadQuizResponse(quizId);
		return response;
	}
	
	@GetMapping("/{quizId}")
	public BaseResponse<GetQuizDetailsResponse> getQuizDetails(@PathVariable("quizId") String quizId, @RequestHeader("token") String userToken){
		UUID userUUId = UUID.fromString(userToken);
		UUID quizUUID = UUID.fromString(quizId);
		SuccessResponse<GetQuizDetailsResponse> response = new SuccessResponse<>("Quiz Details");
		Quiz savedQuiz = quizService.getQuiz(quizUUID);
		savedQuiz.setQuestions(null);
		savedQuiz.setPartnerId(null);

		List<UserActivityLog> userActivityLogs = userActivityService.getUserActions(userUUId, quizUUID);
		boolean videoUploaded = userActivityLogs.contains(new UserActivityLog(userUUId, quizUUID, UserAction.UPLOAD_VIDEO.name()));
		boolean paymentDone = userActivityLogs.contains(new UserActivityLog(userUUId, quizUUID, UserAction.PAYMENT_DONE.name()));
		boolean quizStarted = userActivityLogs.contains(new UserActivityLog(userUUId, quizUUID, UserAction.START_QUIZ.name()));
		boolean quizSubmitted = userActivityLogs.contains(new UserActivityLog(userUUId, quizUUID, UserAction.SUBMIT_QUIZ.name()));
		response.data = new GetQuizDetailsResponse(savedQuiz,new UserQuizActions(videoUploaded, paymentDone,quizStarted, quizSubmitted));
		return response;
	}

	@GetMapping("{quizId}/question")
	public BaseResponse<GetQuizQuestionsResponse> getQuizQuestions(@PathVariable("quizId") String quizId, @RequestHeader("token") String userToken){
		SuccessResponse<GetQuizQuestionsResponse> response = new SuccessResponse<>("Quiz Questions");
		ArrayList<QuestionDetail> questions = (ArrayList<QuestionDetail>) quizService.getQuestions(UUID.fromString(quizId));
		GetQuizQuestionsResponse questionsResponse = new GetQuizQuestionsResponse();
		questionsResponse.setQuestions(questions);
		response.data = questionsResponse;
		return response;
	}

	@PostMapping("{quizId}/start")
	public BaseResponse<StartQuizResponse> startQuiz(@PathVariable("quizId") String quizId, @RequestHeader("token") String userToken){
		SuccessResponse<StartQuizResponse> response = new SuccessResponse<>("Quiz Started");
		Quiz quiz = quizService.getQuiz(UUID.fromString(quizId));
		UserActivityLog startQuizLog = quizService.startQuiz(UUID.fromString(quizId), UUID.fromString(userToken));
		StartQuizResponse startQuizResponse = new StartQuizResponse();
		startQuizResponse.setQuizStartTime(startQuizLog.getCreatedTime());
		startQuizResponse.setDuration(quiz.getDuration());
		response.data = startQuizResponse;
		return response;
	}

	@PostMapping("{quizId}/submit")
	public BaseResponse<EndQuizResponse> submitQuiz(@PathVariable("quizId") String quizId , @RequestBody SubmitQuizRequest submitQuizRequest, @RequestHeader("token") String userToken){
		SuccessResponse<EndQuizResponse> response = new SuccessResponse<>("Quiz Submitted Successfully");
		HashMap<UUID, List<UUID>> answers = new HashMap<>();
		List<Answer> answersArray = submitQuizRequest.getAnswer();
		for(Answer answer : answersArray){
			List<UUID> optionsId = Arrays.asList(answer.getOptions());
			answers.put(answer.getQuestionId(), optionsId);
		}
		Result quizResult = quizService.submitQuiz(UUID.fromString(quizId),UUID.fromString(userToken), answers);
		EndQuizResponse endQuizResponse = new EndQuizResponse();
		endQuizResponse.setResult(quizResult);
		endQuizResponse.setEndTime(quizResult.createdTime);
		response.data = endQuizResponse;
		return response;
	}

	@GetMapping("{quizId}/result")
	public BaseResponse<QuizResultResponse> getQuizResult(@PathVariable("quizId") String quizId, @RequestHeader("token") String userToken){
		SuccessResponse<QuizResultResponse> response = new SuccessResponse<>("Result Found");
		Result quizResult = quizService.getQuizResult(UUID.fromString(quizId),UUID.fromString(userToken));
		QuizResultResponse resultResponse = new QuizResultResponse();
		resultResponse.setResult(quizResult);
		response.data = resultResponse;
		return response;
	}

	@GetMapping("user/{userId}")
	public BaseResponse<UserQuizResponse> getUserQuizzes(@PathVariable("userId") String userId, @RequestHeader("token") String userToken){
		SuccessResponse<UserQuizResponse> response = new SuccessResponse<>("Result Found");
		List<UserQuizResult> quizResult = quizService.getUserSubmittedQuizzes(UUID.fromString(userId));
		UserQuizResponse resultResponse = new UserQuizResponse();
		resultResponse.setSubmissions(quizResult);
		response.data = resultResponse;
		return response;
	}
}
