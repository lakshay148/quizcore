package com.quizcore.quizapp.model.other;

import com.quizcore.quizapp.model.entity.Options;
import com.quizcore.quizapp.model.entity.Question;

import java.util.List;

public class QuestionDetail extends Question {

    List<Options> option;

    public List<Options> getOption() {
        return option;
    }

    public void setOptions(List<Options> options) {
        this.option = options;
    }
}
