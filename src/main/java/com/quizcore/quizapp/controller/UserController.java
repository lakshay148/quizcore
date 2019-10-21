package com.quizcore.quizapp.controller;

import java.util.UUID;

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
	com.quizcore.quizapp.service.UserService userService;
	
	
	@GetMapping("/healthcheck")
	public BaseResponse<Object> checkHealth() {
		BaseResponse<Object> response = new BaseResponse<>();
		response.message = "It works awesone";
		response.status = 200;
		return response;
	}
	
	@PostMapping("/register")
	public BaseResponse<RegistrationResponse> registerUser(@RequestBody RegisterRequest registermodel)
	{
		User user = new User(registermodel.getName(), registermodel.getEmail(), registermodel.getPassword(), registermodel.getPhone());
		User userId = userService.getUserByEmail(user);
		if(userId!=null)
		{   
			BaseResponse<RegistrationResponse> response = new BaseResponse<>();
			response.message = "User already exists";
			response.status = 200;
			return response;
		}

		UUID uuid = userService.addUser(user);
		BaseResponse<RegistrationResponse> response = new BaseResponse<>();
		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setId(uuid);
		response.message = "User registered successfully";
		response.status = 201;
		response.data = registrationResponse;
		return response;
	}
	
	
	 @PostMapping("/login")
	 public BaseResponse<LoginResponse> loginUser(@RequestBody com.quizcore.quizapp.model.network.request.user.LoginRequest loginmodel)
		{
			if(loginmodel == null || loginmodel.getPassword() == null) {
				BaseResponse<LoginResponse> response = new BaseResponse<>();
				response.message =" Incorrect email/password";
				response.status = 200;
				return response;
			}
			User user = new User(loginmodel.getEmail(),loginmodel.getPassword());
			User savedUser = userService.getUserByEmail(user);
			if(loginmodel.getPassword().equals(savedUser.getPassword())) 
			{
				BaseResponse<LoginResponse> response = new BaseResponse<>();
				LoginResponse loginResponse = new LoginResponse();
				loginResponse.setEmail(savedUser.email);
				loginResponse.setId(savedUser.id);
				loginResponse.setName(savedUser.getName());
				response.message = "User login successful !!";
				response.status = 200;
				response.data = loginResponse;
				return response;

			} 
			else 
			{
				BaseResponse<LoginResponse> response = new BaseResponse<>();
				response.message = " Incorrect email/password";
				return response;
			}
		}

}

