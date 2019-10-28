package com.quizcore.quizapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.quizcore.quizapp.model.entity.Options;
import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.network.request.quiz.AddQuizRequest;
import com.quizcore.quizapp.model.repository.OptionsRespository;
import com.quizcore.quizapp.model.repository.QuestionRepository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.quiz.AddQuizResponse;
import com.quizcore.quizapp.model.network.response.quiz.GetQuizResponse;
import com.quizcore.quizapp.model.network.response.quiz.UploadQuizResponse;
import com.quizcore.quizapp.service.QuizService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("quizcore/api/v1/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;

	@Autowired
	OptionsRespository optionsRespository;

	@Autowired
	QuestionRepository questionRepository;
	
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
	public SuccessResponse<UploadQuizResponse> uploadQuiz(@RequestParam("file") MultipartFile quizFile) throws IOException {
		SuccessResponse<UploadQuizResponse> response = new SuccessResponse<>("It works awesone");
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

		Quiz quiz = new Quiz(UUID.fromString("46f7c71a-79c8-4c71-a945-037fe3cee855"), title, description, instructions, level, subject, category, duration, price, type, correctMarks, inCorrectMarks);

		List<Question> questionList = new ArrayList<Question>();
		String questions = null;

		for(int k=1;k<questionDetails.getPhysicalNumberOfRows() ;k++) {
			Question question = new Question();
			XSSFRow row = questionDetails.getRow(k);

			question.setLevel(level);
			question.setStatement(row.getCell(0).getStringCellValue());
			question.setSubject(subject);

			Options savedOption1 =  optionsRespository.save(new Options(row.getCell(1).getStringCellValue()));
			Options savedOption2 =  optionsRespository.save(new Options(row.getCell(2).getStringCellValue()));
			Options savedOption3 =  optionsRespository.save(new Options(row.getCell(3).getStringCellValue()));
			Options savedOption4 =  optionsRespository.save(new Options(row.getCell(4).getStringCellValue()));


			question.setAnswer("");
			question.setType(type);
			question.setOptions(savedOption1.id + "," + savedOption2.id + "," + savedOption3.id+"," + savedOption4.id);

			Question savedQuestion = questionRepository.save(question);
			questionList.add(savedQuestion);

			questions = questions == null ? question.id + "" : questions+","+question.id;

			System.out.println("question " + savedQuestion.toString());
		}

		quiz.setQuestions(questions);

		quizService.uploadQuiz(quiz);

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
