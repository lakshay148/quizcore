package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Job;
import com.quizcore.quizapp.model.network.request.job.AddJobRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.job.AddJobResponse;
import com.quizcore.quizapp.model.network.response.job.JobsResponse;
import com.quizcore.quizapp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
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

    @GetMapping("/partner/{partnerId}")
    public SuccessResponse<JobsResponse> getPartnerJobs(@PathVariable("partnerId") String partnerId) {
        SuccessResponse<JobsResponse> response = new SuccessResponse<>("Partner Jobs");
        List<Job> partnerJobs = jobService.getPartnerJobs(UUID.fromString(partnerId));
        response.data = new JobsResponse(partnerJobs);
        return response;
    }

    @GetMapping
    public SuccessResponse<JobsResponse> getJobs() {
        SuccessResponse<JobsResponse> response = new SuccessResponse<>("Jobs list");
        List<Job> partnerJobs = jobService.getActiveJobs();
        response.data = new JobsResponse(partnerJobs);
        return response;
    }

}
