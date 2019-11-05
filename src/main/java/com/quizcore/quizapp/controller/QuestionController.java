package com.quizcore.quizapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.quizcore.quizapp.model.entity.Options;
import com.quizcore.quizapp.model.repository.OptionsRespository;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.network.request.question.AddQuestionRequest;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
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

	@Autowired
	OptionsRespository optionsRespository;
	
	@GetMapping("/healthcheck")
	public SuccessResponse<Object> checkHealth() {
		SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
		return response;
	}
	
	@PostMapping("/add")
	public SuccessResponse<AddQuestionResponse> addQuestion(@RequestBody AddQuestionRequest request){
		Question question = new Question(request.getStatement(), request.getType(),request.getLevel(), request.getSubject());
		UUID questionId = questionService.addQuestion(question);
		SuccessResponse<AddQuestionResponse> response = new SuccessResponse<>("It works awesone");
		AddQuestionResponse addQuestion = new AddQuestionResponse();
		addQuestion.questionId = questionId;
		response.data = addQuestion;
		return response;
	}
	
	@PostMapping("/upload")
	public SuccessResponse<UploadQuestionResponse> uploadQuestion(@RequestParam("file") MultipartFile questionFile, @RequestParam("type") String questionFileType) throws IOException {
		SuccessResponse<UploadQuestionResponse> response = new SuccessResponse<>("It works awesone");

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
			Options savedOption1 =  optionsRespository.save(new Options(row.getCell(1).getStringCellValue()));
			Options savedOption2 =  optionsRespository.save(new Options(row.getCell(2).getStringCellValue()));
			Options savedOption3 =  optionsRespository.save(new Options(row.getCell(3).getStringCellValue()));
			Options savedOption4 =  optionsRespository.save(new Options(row.getCell(4).getStringCellValue()));

			question.setAnswer("");
			question.setType("MCQ");
			question.setOptions("");
			questionList.add(question);

			System.out.println("question " + question.toString());
		}
		UploadQuestionResponse questionResponse = new UploadQuestionResponse();
		questionResponse.question = questionList.get(0).statement;
		response.data = questionResponse;
		return response;
	}
	
	@GetMapping("/{questionId}")
	public SuccessResponse<GetQuestionResponse> getQuestion(@PathVariable("questionId") String questionId){
		SuccessResponse<GetQuestionResponse> response = new SuccessResponse<>("It works awesone");
		return response;
	}
	
}
