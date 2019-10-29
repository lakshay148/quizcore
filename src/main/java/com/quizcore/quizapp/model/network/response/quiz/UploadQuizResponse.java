package com.quizcore.quizapp.model.network.response.quiz;

import java.util.UUID;

public class UploadQuizResponse {

    UUID quizId;

    public UploadQuizResponse(UUID quizId) {
        this.quizId = quizId;
    }

    public UUID getQuizId() {
        return quizId;
    }

    public void setQuizId(UUID quizId) {
        this.quizId = quizId;
    }
}
