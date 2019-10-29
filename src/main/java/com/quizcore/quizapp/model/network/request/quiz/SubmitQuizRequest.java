package com.quizcore.quizapp.model.network.request.quiz;

import com.quizcore.quizapp.model.Answer;

import java.util.List;

public class SubmitQuizRequest {

    public List<Answer> answer;

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
}
