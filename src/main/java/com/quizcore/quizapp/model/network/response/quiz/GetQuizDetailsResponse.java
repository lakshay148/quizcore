package com.quizcore.quizapp.model.network.response.quiz;

import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.other.UserQuizActions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuizDetailsResponse {

    Quiz quizDetails;
    UserQuizActions userQuizActions;
}
