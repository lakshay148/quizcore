package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.entity.Result;
import com.quizcore.quizapp.service.base.IResultService;

import java.util.UUID;

public class ResultService implements IResultService {
    @Override
    public Result computeResult(Quiz quiz) {
        return null;
    }

    @Override
    public Result getResult(UUID resultId) {
        return null;
    }
}
