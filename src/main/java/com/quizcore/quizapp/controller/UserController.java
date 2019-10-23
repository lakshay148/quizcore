package com.quizcore.quizapp.controller;

import java.util.UUID;

import com.quizcore.quizapp.model.network.request.user.LoginRequest;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.network.request.user.RegisterRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.user.LoginResponse;
import com.quizcore.quizapp.model.network.response.user.RegistrationResponse;

@RestController
@RequestMapping("quizcore/api/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/healthcheck")
	public BaseResponse<Object> checkHealth() {
		SuccessResponse<Object> response = new SuccessResponse<>("It works awesome");
		return response;
	}
	
	@PostMapping("/register")
	public BaseResponse<RegistrationResponse> registerUser(@RequestBody RegisterRequest registermodel)
	{
		User user = new User(registermodel.getName(), registermodel.getEmail(), registermodel.getPassword(), registermodel.getPhone());
		User userId = userService.getUserByEmailOrPhone(user);

		if(userId!=null)
		{   
			ErrorResponse<RegistrationResponse> response = new ErrorResponse<>("User already exists", null);
			return response;
		}

		UUID uuid = userService.addUser(user);
		SuccessResponse<RegistrationResponse> response = new SuccessResponse<>("User registered successfully");
		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setId(uuid);
		response.data = registrationResponse;

		return response;
	}
	
	
	@PostMapping("/login")
	public BaseResponse<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest)
	{
		if(loginRequest == null || loginRequest.getPassword() == null) {
			ErrorResponse<LoginResponse> response = new ErrorResponse<>("Incorrect email/password", null);
			return response;
		}
		User user = new User(loginRequest.getEmail(),loginRequest.getPassword());
		User savedUser = userService.getUserByEmail(user);
		if(savedUser != null && loginRequest.getPassword().equals(savedUser.getPassword()))
		{
			SuccessResponse<LoginResponse> response = new SuccessResponse<>("User login successful !!");
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setEmail(savedUser.email);
			loginResponse.setId(savedUser.id);
			loginResponse.setName(savedUser.getName());
			response.data = loginResponse;
			return response;
		}
		else
		{
			ErrorResponse<LoginResponse> response = new ErrorResponse<>(" Incorrect email/password", null);
			return response;
		}
	}

}

