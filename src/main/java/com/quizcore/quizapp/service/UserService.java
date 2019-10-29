
package com.quizcore.quizapp.service;

import java.util.Optional;
import java.util.UUID;

import com.quizcore.quizapp.service.base.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.repository.UserRepository;


@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User getUserById(User user) {
		Optional<User> savedUserOptional = userRepository.findById(user.id);
		User savedUser = savedUserOptional.get();
		return savedUser;
	}

	@Override
	public UUID addUser(User user) {
		User userSaved = userRepository.save(user);
		return userSaved.id;
	}

	@Override
	public User getUserByEmail(User user) {
		User savedUser = userRepository.findByEmail(user.getEmail());
		return savedUser;
	}

	@Override
	public User getUserByEmailOrPhone(User user) {
		User savedUser = userRepository.findByEmailOrPhone(user.getEmail(), user.getPhone());
		return savedUser;
	}

}
