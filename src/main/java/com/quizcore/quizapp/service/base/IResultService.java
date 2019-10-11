package com.quizcore.quizapp.service.base;

import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.entity.Result;

import java.util.UUID;

public interface IResultService {
    //TODO add user answers as input params
    Result computeResult(Quiz quiz);
    Result getResult(UUID resultId);

}
