package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.entity.Result;
import com.quizcore.quizapp.model.repository.ResultRepository;
import com.quizcore.quizapp.service.base.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultService implements IResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public Result computeResult(Quiz quiz) {
        return null;
    }

    @Override
    public Result getResult(UUID resultId) {
        return null;
    }


    public  List<Result> getSubmissions(UUID productId){
        List<Result> results = (List<Result>) resultRepository.findAll();
        return results;
    }
}
