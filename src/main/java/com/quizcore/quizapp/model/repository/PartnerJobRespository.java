package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Job;
import com.quizcore.quizapp.model.entity.PartnerJob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartnerJobRespository extends CrudRepository<PartnerJob, UUID> {

    @Query("select new com.quizcore.quizapp.model.entity.Job(a.id,a.title,a.description,a.city,a.startTime,a.endTime,a.salary) from Job a, PartnerJob b where a.id=b.jobId and b.partnerId=?1")
    List<Job> findPartnerJobs(UUID partnerId);
}
