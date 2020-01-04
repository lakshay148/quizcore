
package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.entity.UserQuizMedia;
import com.quizcore.quizapp.model.repository.UserQuizMediaRepository;
import com.quizcore.quizapp.model.repository.UserRepository;
import com.quizcore.quizapp.service.base.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserService implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserQuizMediaRepository userProductMediaRepository;

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

	public UUID saveUserMedia(UserQuizMedia userProductMedia){
		UserQuizMedia userProductMediaSaved = userProductMediaRepository.save(userProductMedia);
		return userProductMediaSaved.id;
	}

}
