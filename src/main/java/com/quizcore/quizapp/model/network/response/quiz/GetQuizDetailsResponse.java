package com.quizcore.quizapp.model.network.response.quiz;

import com.quizcore.quizapp.model.entity.Quiz;

public class GetQuizDetailsResponse {

    Quiz quizDetails;

    public GetQuizDetailsResponse(Quiz quizDetails) {
        this.quizDetails = quizDetails;
    }

    public Quiz getQuizDetails() {
        return quizDetails;
    }

    public void setQuizDetails(Quiz quizDetails) {
        this.quizDetails = quizDetails;
    }
}
