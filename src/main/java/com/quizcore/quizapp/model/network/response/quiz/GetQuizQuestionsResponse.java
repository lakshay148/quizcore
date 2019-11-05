package com.quizcore.quizapp.model.network.response.quiz;

import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.other.QuestionDetail;

import java.util.List;

public class GetQuizQuestionsResponse {

    List<QuestionDetail> questions;

    public List<QuestionDetail> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDetail> questions) {
        this.questions = questions;
    }
}
