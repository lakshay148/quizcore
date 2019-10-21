package com.quizcore.quizapp.model.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quizcore.quizapp.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>{
	
	User findByEmail(String email);
	
}
