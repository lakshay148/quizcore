package com.quizcore.quizapp.model.network.response.quiz;

import com.quizcore.quizapp.model.entity.Quiz;
import lombok.Data;

import java.util.List;

@Data
public class GetQuizzesResponse {
    List<Quiz> quizzes;
}
