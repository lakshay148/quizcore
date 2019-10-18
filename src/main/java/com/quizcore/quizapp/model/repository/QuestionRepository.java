package com.quizcore.quizapp.model.repository;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quizcore.quizapp.model.entity.Question;

@Repository
public interface QuestionRepository /*extends CrudRepository<Question, UUID>*/ {

}
