package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResultRepository extends CrudRepository<Result, UUID> {
    Result findByQuizIdAndUserId(UUID quizId, UUID userId);
}
