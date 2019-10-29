package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Options;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OptionsRespository extends CrudRepository<Options, UUID> {
}
