package com.quizcore.quizapp.controller;

import java.io.IOException;
import java.util.*;

import com.quizcore.quizapp.model.Answer;
import com.quizcore.quizapp.model.entity.*;
import com.quizcore.quizapp.model.network.request.quiz.AddQuizRequest;
import com.quizcore.quizapp.model.network.request.quiz.SubmitQuizRequest;
import com.quizcore.quizapp.model.network.response.quiz.*;
import com.quizcore.quizapp.model.other.QuestionDetail;
import com.quizcore.quizapp.model.repository.OptionsRespository;
import com.quizcore.quizapp.model.repository.QuestionRepository;
import com.quizcore.quizapp.model.repository.UserActivityRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.service.QuizService;
import org.springframework.web.multipart.MultipartFile;

@RestController
//@RequestMapping("api/v1/quiz")
@RequestMapping("quizcore/api/v1/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	@Autowired
	OptionsRespository optionsRespository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	UserActivityRepository userActivityRepository;
	
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
	public SuccessResponse<UploadQuizResponse> uploadQuiz(@RequestParam("file") MultipartFile quizFile, @RequestParam(value = "partnerId", required = true) String partnerId) throws IOException {
		SuccessResponse<UploadQuizResponse> response = new SuccessResponse<>("Quiz uploaded");
		XSSFWorkbook workbook = new XSSFWorkbook(quizFile.getInputStream());
		XSSFSheet quizDetails = workbook.getSheetAt(0);
		XSSFSheet questionDetails = workbook.getSheetAt(1);

		System.out.println("rows " + quizDetails.getPhysicalNumberOfRows());
		System.out.println("rows " + questionDetails.getPhysicalNumberOfRows());

		int i=0;
		String title = quizDetails.getRow(i +1).getCell(1).getStringCellValue();
		String description = quizDetails.getRow(i+2).getCell(1).getStringCellValue();
		int level = ((int)quizDetails.getRow(i+3).getCell(1).getNumericCellValue());
		String instructions = quizDetails.getRow(i+4).getCell(1).getStringCellValue();
		String subject = quizDetails.getRow(i+5).getCell(1).getStringCellValue();
		String category = quizDetails.getRow(i+6).getCell(1).getStringCellValue();
		int duration =  ((int)quizDetails.getRow(i+7).getCell(1).getNumericCellValue());
		String type = quizDetails.getRow(i+8).getCell(1).getStringCellValue();
		int correctMarks = (int)quizDetails.getRow(i+9).getCell(1).getNumericCellValue();
		int inCorrectMarks = (int)quizDetails.getRow(i+10).getCell(1).getNumericCellValue();
		double price = quizDetails.getRow(i+11).getCell(1).getNumericCellValue();


		for(int j=1; j< quizDetails.getPhysicalNumberOfRows();j++){
			XSSFRow row = quizDetails.getRow(j);
			System.out.println("key "+row.getCell(0) + " value " + row.getCell(1));
		}

		Quiz quiz = new Quiz(UUID.fromString(partnerId), title, description, instructions, level, subject, category, duration, price, type, correctMarks, inCorrectMarks);

		List<Question> questionList = new ArrayList<Question>();
		String questions = null;

		for(int k=1;k<questionDetails.getPhysicalNumberOfRows() ;k++) {
			Question question = new Question();
			XSSFRow row = questionDetails.getRow(k);

			question.setLevel(level);
			question.setStatement(row.getCell(0).getStringCellValue());
			question.setSubject(subject);

			Options[] options = new Options[4];

			Options savedOption1 =  optionsRespository.save(new Options(row.getCell(1).getStringCellValue()));
			options[0] = savedOption1;
			Options savedOption2 =  optionsRespository.save(new Options(row.getCell(2).getStringCellValue()));
			options[1] = savedOption2;

			Options savedOption3 =  optionsRespository.save(new Options(row.getCell(3).getStringCellValue()));
			options[2] = savedOption3;

			Options savedOption4 =  optionsRespository.save(new Options(row.getCell(4).getStringCellValue()));
			options[3] = savedOption4;

			String answer = row.getCell(5).toString();;
			String[] answersArray  = answer.split(",");
			int p = 0;
			String answerIds = "";
            for(p=0;p<answersArray.length;p++){
                String optionNo = answersArray[p];
                Options answerOption = options[((int) Double.parseDouble(optionNo)-1)];
                answerIds = answerIds.length() <= 1 ? answerOption.id+"":answerIds+","+answerOption.id;
            }
			question.setAnswer(answerIds);
			question.setType(type);
			question.setOptions(savedOption1.id + "," + savedOption2.id + "," + savedOption3.id+"," + savedOption4.id);

			Question savedQuestion = questionRepository.save(question);
			questionList.add(savedQuestion);

			questions = questions == null ? question.id + "" : questions+","+question.id;

			System.out.println("question " + savedQuestion.toString());
		}

		quiz.setQuestions(questions);

		UUID quizId = quizService.uploadQuiz(quiz);
		response.data = new UploadQuizResponse(quizId);
		return response;
	}
	
	@GetMapping("/{quizId}")
	public SuccessResponse<GetQuizDetailsResponse> getQuizDetails(@PathVariable("quizId") String quizId){
		SuccessResponse<GetQuizDetailsResponse> response = new SuccessResponse<>("Quiz Details");
		Quiz savedQuiz = quizService.getQuiz(UUID.fromString(quizId));
		savedQuiz.setQuestions(null);
		savedQuiz.setPartnerId(null);
		response.data = new GetQuizDetailsResponse(savedQuiz);
		return response;
	}

	@GetMapping("{quizId}/question")
	public SuccessResponse<GetQuizQuestionsResponse> getQuizQuestions(@PathVariable("quizId") String quizId){
		SuccessResponse<GetQuizQuestionsResponse> response = new SuccessResponse<>("Quiz Questions");
		ArrayList<QuestionDetail> questions = (ArrayList<QuestionDetail>) quizService.getQuestions(UUID.fromString(quizId));
		GetQuizQuestionsResponse questionsResponse = new GetQuizQuestionsResponse();
		questionsResponse.setQuestions(questions);
		response.data = questionsResponse;
		return response;
	}

	@PostMapping("{quizId}/start")
	public SuccessResponse<StartQuizResponse> startQuiz(@PathVariable("quizId") String quizId, @RequestHeader("token") String userToken){
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
	public SuccessResponse<EndQuizResponse> submitQuiz(@PathVariable("quizId") String quizId , @RequestBody SubmitQuizRequest submitQuizRequest, @RequestHeader("token") String userToken){
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
	public SuccessResponse<QuizResultResponse> getQuizResult(@PathVariable("quizId") String quizId, @RequestHeader("token") String userToken){
		SuccessResponse<QuizResultResponse> response = new SuccessResponse<>("Result Found");
		Result quizResult = quizService.getQuizResult(UUID.fromString(quizId), UUID.fromString(userToken));
		QuizResultResponse resultResponse = new QuizResultResponse();
		resultResponse.setResult(quizResult);
		response.data = resultResponse;
		return response;
	}
}
