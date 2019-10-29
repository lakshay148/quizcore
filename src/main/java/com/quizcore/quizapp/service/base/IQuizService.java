package com.quizcore.quizapp.service.base;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.entity.Result;

public interface IQuizService {
	UUID addQuiz(Quiz quiz);
	Quiz getQuiz(UUID quizId);
	UUID uploadQuiz(Quiz quiz);
	Result submitQuiz(UUID quizid, UUID userId, Map<UUID, List<UUID>> answers);
}
