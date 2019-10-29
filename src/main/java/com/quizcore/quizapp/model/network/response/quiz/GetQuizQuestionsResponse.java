package com.quizcore.quizapp.model.network.response.quiz;

import com.quizcore.quizapp.model.entity.Question;

import java.util.List;

public class GetQuizQuestionsResponse {

    List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
