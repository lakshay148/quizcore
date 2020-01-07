package com.quizcore.quizapp.model.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserQuizResult {
    String result;
    double score;
    UUID quizId;
    String description;
    String title;
    String partnerId;

    public UserQuizResult(String result, double score, UUID quizId, String description, String title) {
        this.result = result;
        this.score = score;
        this.quizId = quizId;
        this.description = description;
        this.title = title;
    }
}
