package com.quizcore.quizapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.quizcore.quizapp.model.Answer;
import com.quizcore.quizapp.model.other.Option;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.network.request.question.AddQuestionRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.question.AddQuestionResponse;
import com.quizcore.quizapp.model.network.response.question.GetQuestionResponse;
import com.quizcore.quizapp.model.network.response.question.UploadQuestionResponse;
import com.quizcore.quizapp.service.QuestionService;
import org.springframework.web.multipart.MultipartFile;

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
	public BaseResponse<UploadQuestionResponse> uploadQuestion(@RequestParam("file") MultipartFile questionFile, @RequestParam("type") String questionFileType) throws IOException {
		BaseResponse<UploadQuestionResponse> response = new BaseResponse<>();

		List<Question> questionList = new ArrayList<Question>();
		XSSFWorkbook workbook = new XSSFWorkbook(questionFile.getInputStream());
		XSSFSheet worksheet = workbook.getSheetAt(0);
		System.out.println("rows " + worksheet.getPhysicalNumberOfRows());
		for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
			Question question = new Question();
			XSSFRow row = worksheet.getRow(i);

			question.setLevel((int) row.getCell(6).getNumericCellValue());
			question.setStatement(row.getCell(0).getStringCellValue());
			question.setSubject(row.getCell(7).getStringCellValue());
			Option option1 = new Option();
			option1.setText(row.getCell(1).getStringCellValue());
			Option option2 = new Option();
			option2.setText(row.getCell(2).getStringCellValue());
			Option option3 = new Option();
			option3.setText(row.getCell(3).getStringCellValue());
			Option option4 = new Option();
			option4.setText(row.getCell(4).getStringCellValue());

			List<Option> options = new ArrayList<>();
			options.add(option1);
			options.add(option2);
			options.add(option3);
			options.add(option4);
			row.getCell(5).getNumericCellValue();

			Answer answer = new Answer();
			int[] answers = new int[2];
			answer.options = answers;
			question.setAnswer(answer);
			question.setType("MCQ");
			question.setOptions(options);
			questionList.add(question);

			System.out.println("question " + question.toString());
		}
		UploadQuestionResponse questionResponse = new UploadQuestionResponse();
		questionResponse.question = questionList.get(0).statement;
		response.message = "It works awesone";
		response.status = 200;
		response.data = questionResponse;
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
