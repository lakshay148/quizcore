package com.quizcore.quizapp.model.network.response.quiz;

import com.quizcore.quizapp.model.entity.Result;

import java.time.LocalDateTime;

public class EndQuizResponse extends Result{

    LocalDateTime endTime;

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

}
