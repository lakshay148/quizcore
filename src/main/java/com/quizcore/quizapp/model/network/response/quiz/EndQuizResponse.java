package com.quizcore.quizapp.model.network.response.quiz;

import com.quizcore.quizapp.model.entity.Result;

import java.time.LocalDateTime;

public class EndQuizResponse {

    LocalDateTime endTime;
    Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
