package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID>{
	
	User findByEmail(String email);
	User findByEmailOrPhone(String email, String mobile);
	List<User> findAllByProductId(UUID productId);
}
