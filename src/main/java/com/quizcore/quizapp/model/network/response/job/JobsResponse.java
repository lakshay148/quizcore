package com.quizcore.quizapp.model.network.response.job;

import com.quizcore.quizapp.model.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobsResponse {
    List<Job> jobs;
}
