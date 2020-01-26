package com.quizcore.quizapp.model.network.response.product;

import com.quizcore.quizapp.model.entity.Result;
import lombok.Data;

import java.util.List;

@Data
public class GetSubmissionsResponse {
    List<Result> submissions;
}
