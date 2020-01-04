package com.quizcore.quizapp.model.network.request.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SubmitVideoRequest {

    String videoId;
    String quizId;

}
