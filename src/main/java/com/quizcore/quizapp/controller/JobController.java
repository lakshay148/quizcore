package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Job;
import com.quizcore.quizapp.model.network.request.job.AddJobRequest;
import com.quizcore.quizapp.model.network.response.job.AddJobResponse;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${base.endpoint}/api/v1/job")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/healthcheck")
    public SuccessResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesome");
        return response;
    }

    @PostMapping
    public BaseResponse<AddJobResponse> addJob(@RequestBody AddJobRequest addJobRequest) {
        SuccessResponse<AddJobResponse> response = new SuccessResponse<>("Job Added");
        UUID jobId = jobService.addJob(UUID.fromString(addJobRequest.getPartnerId()), new Job(addJobRequest.getTitle(), addJobRequest.getDescription(), addJobRequest.getCity(), addJobRequest.getStartTime(), addJobRequest.getEndTime(), addJobRequest.getSalaryPerMonth()) );
        AddJobResponse jobResponse = new AddJobResponse();
        jobResponse.setId(jobId.toString());
        response.data = jobResponse;
        return response;
    }

}
