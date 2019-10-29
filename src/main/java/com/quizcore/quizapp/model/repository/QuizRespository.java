package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuizRespository extends CrudRepository<Quiz, UUID> {
}
