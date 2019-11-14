package com.quizcore.quizapp.service;

import java.util.*;
import java.util.stream.Collectors;

import com.quizcore.quizapp.model.entity.*;
import com.quizcore.quizapp.model.repository.*;
import com.quizcore.quizapp.model.entity.*;
import com.quizcore.quizapp.model.other.QuestionDetail;
import com.quizcore.quizapp.model.repository.*;
import com.quizcore.quizapp.service.base.IQuizService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
