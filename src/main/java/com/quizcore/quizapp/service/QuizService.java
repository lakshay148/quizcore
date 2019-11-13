package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.*;
import com.quizcore.quizapp.model.other.QuestionDetail;
import com.quizcore.quizapp.model.repository.*;
import com.quizcore.quizapp.service.base.IQuizService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService implements IQuizService {

	@Autowired
	QuizRespository quizRespository;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	ResultRepository resultRepository;

	@Autowired
	OptionsRespository optionsRespository;

	@Autowired
	UserActivityRepository userActivityRepository;

	@Override
	public UUID addQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quiz getQuiz(UUID quizId) {
		Optional<Quiz> quizOptional = quizRespository.findById(quizId);
		if(quizOptional.isPresent()){
			return quizOptional.get();
		} else {
			return null;
		}
	}

	@Override
	public UUID uploadQuiz(Quiz quiz) {
		Quiz savedQuiz = quizRespository.save(quiz);
		return savedQuiz.id;
	}

	private Quiz parseQuizDetails(XSSFSheet sheet){
		System.out.println("rows " + sheet.getPhysicalNumberOfRows());
		int i=0;
		XSSFSheet quizDetails = sheet;

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

		Quiz quiz = new Quiz(title, description, instructions, level, subject, category, duration, price, type, correctMarks, inCorrectMarks);
		return quiz;
	}

	private Quiz parseQuizQuestions(Quiz quiz , XSSFSheet sheet){
		XSSFSheet questionDetails = sheet;
		System.out.println("rows " + questionDetails.getPhysicalNumberOfRows());

		List<Question> questionList = new ArrayList<Question>();
		String questions = null;

		for(int k=1;k<questionDetails.getPhysicalNumberOfRows() ;k++) {
			Question question = new Question();
			XSSFRow row = questionDetails.getRow(k);

			question.setLevel(quiz.getLevel());
			question.setStatement(row.getCell(0).getStringCellValue());
			question.setSubject(quiz.getSubject());

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
			question.setType(quiz.getType());
			question.setOptions(savedOption1.id + "," + savedOption2.id + "," + savedOption3.id+"," + savedOption4.id);

			Question savedQuestion = questionRepository.save(question);
			questionList.add(savedQuestion);

			questions = questions == null ? question.id + "" : questions+","+question.id;

			System.out.println("question " + savedQuestion.toString());
		}
		quiz.setQuestions(questions);
		return quiz;
	}

	public UUID uploadQuiz(UUID partnerId, MultipartFile quizFile) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook(quizFile.getInputStream());
		XSSFSheet quizDetails = workbook.getSheetAt(0);

		Quiz quiz = parseQuizDetails(quizDetails);

		quiz.setPartnerId(partnerId);

		XSSFSheet questionDetails = workbook.getSheetAt(1);

		Quiz quizWithQuestions = parseQuizQuestions(quiz, questionDetails);

		return uploadQuiz(quizWithQuestions);
	}

	@Override
	public Result submitQuiz(UUID quizid, UUID userId, Map<UUID, List<UUID>> answers) {
		Set<UUID> questionIds = answers.keySet();
		Quiz quiz = getQuiz(quizid);
		ArrayList<Question> questions = (ArrayList<Question>) questionRepository.findAllById(questionIds);
		List<UUID> correctQuestions = new ArrayList<>();
		List<UUID> inCorrectQuestions = new ArrayList<>();
		for(int i=0; i<questions.size();i++){
			Question question = questions.get(i);
			String answer = question.getAnswer();
			String[] answerOptions = answer.split(",");
			List<UUID> submittedAnswer = answers.get(question.id);
			boolean attempted = false;
			boolean incorrect = false;
			if(submittedAnswer != null && submittedAnswer.size()>0){
				attempted = true;
				for(String actualAnswer : answerOptions){
					if(!submittedAnswer.contains(UUID.fromString(actualAnswer))){
						inCorrectQuestions.add(question.id);
						incorrect = true;
						continue;
					}
				}
				if(!incorrect && attempted){
					correctQuestions.add(question.id);
				}
			}
		}
		int score = quiz.getCorrectMarks() * correctQuestions.size() + quiz.getIncorrectMarks()*inCorrectQuestions.size();
		int passCriteria = quiz.getPassingCriteria();
		String[] quizQuestions = quiz.getQuestions().split(",");
		int totalMarks = quizQuestions.length * quiz.getCorrectMarks();
		String result = score/totalMarks*100 > passCriteria ? "pass":"fail";

		Result quizResult = new Result(quizid, userId, score);
		quizResult.setResult(result);

		Result savedResult = resultRepository.save(quizResult);

		UserActivityLog activityLog = new UserActivityLog(userId, "QUIZ_SUBMIT");
		activityLog.setQuizId(quizid);

		UserActivityLog savedActivityLog = userActivityRepository.save(activityLog);
		savedResult.setCreatedTime(savedActivityLog.createdTime);

		return savedResult;
	}

	public List<QuestionDetail> getQuestions(UUID quizId){
		Quiz savedQuiz = getQuiz(quizId);
		if(savedQuiz != null){
			String questionIds = savedQuiz.getQuestions();
			String[] questionsArray = questionIds.split(",");
			List<String> questionIdsString = Arrays.asList(questionsArray);
			ArrayList<UUID> questionUUIDs = (ArrayList<UUID>) questionIdsString.stream().map(id -> UUID.fromString(id)).collect(Collectors.toList());
			ArrayList<Question> questions = (ArrayList<Question>) questionRepository.findAllById(questionUUIDs);
			ArrayList<QuestionDetail> questionDetails = getQuestionDetails(questions);
			return questionDetails;
		} else {
			return null;
		}
	}

	private ArrayList<QuestionDetail> getQuestionDetails(ArrayList<Question> questions){
		ArrayList<QuestionDetail> questionDetails = new ArrayList<>();
		for(Question question : questions){
			List<String> optionString = Arrays.asList(question.options.split(","));
			ArrayList<UUID> optionIds = (ArrayList<UUID>) optionString.stream().map(id->UUID.fromString(id)).collect(Collectors.toList());
			List<Options> options = (List<Options>) optionsRespository.findAllById(optionIds);
			QuestionDetail questionDetail = new QuestionDetail();
			BeanUtils.copyProperties(question,questionDetail);
			questionDetail.setOptions(options);
			questionDetails.add(questionDetail);
		}
		return questionDetails;
	}

	public UserActivityLog startQuiz(UUID quizId, UUID userId){
		UserActivityLog activityLog = new UserActivityLog(userId, "QUIZ_START");
		activityLog.setQuizId(quizId);
		UserActivityLog savedLog = userActivityRepository.save(activityLog);
		return savedLog;
	}

	public Result getQuizResult(UUID quizId, UUID userId){
		Result savedResult = resultRepository.findByQuizIdAndUserId(quizId, userId);
		return savedResult;
	}

}
