package com.quizcore.quizapp.model.network.response.quiz;

import java.time.LocalDateTime;

public class StartQuizResponse {

    LocalDateTime quizStartTime;
    int duration;

    public LocalDateTime getQuizStartTime() {
        return quizStartTime;
    }

    public void setQuizStartTime(LocalDateTime quizStartTime) {
        this.quizStartTime = quizStartTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
