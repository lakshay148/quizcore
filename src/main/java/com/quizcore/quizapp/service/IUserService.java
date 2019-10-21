package com.quizcore.quizapp.service;

import java.util.UUID;

import com.quizcore.quizapp.model.entity.User;


public interface IUserService {
	
	User getUserById(User user);
	UUID addUser(User user);
	User getUserByEmail(User user);

}
