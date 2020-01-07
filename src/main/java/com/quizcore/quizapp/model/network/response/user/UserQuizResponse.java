package com.quizcore.quizapp.model.network.response.user;

import com.quizcore.quizapp.model.other.UserQuizResult;
import lombok.Data;

import java.util.List;

@Data
public class UserQuizResponse {
    List<UserQuizResult> submissions;
}
