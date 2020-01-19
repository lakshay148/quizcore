package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Job;
import com.quizcore.quizapp.model.entity.PartnerJob;
import com.quizcore.quizapp.model.repository.JobRepository;
import com.quizcore.quizapp.model.repository.PartnerJobRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    PartnerJobRespository partnerJobRespository;

    public UUID addJob(UUID partnerId, Job job){
        Job savdJob = jobRepository.save(job);
        PartnerJob partnerJob = new PartnerJob(partnerId, savdJob.id);
        partnerJobRespository.save(partnerJob);
        return savdJob.id;
    }

    public List<Job> getPartnerJobs(UUID partnerId){
        return partnerJobRespository.findPartnerJobs(partnerId);
    }

    public List<Job> getActiveJobs(){
        return (List<Job>) jobRepository.findAll();
    }
}
