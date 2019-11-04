package com.quizcore.quizapp.model.network.response.partner;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Quiz;

import java.util.List;

public class GetPartnerQuizResponse {

    List<Quiz> quizes;

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }
}
