package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.network.request.user.LoginRequest;
import com.quizcore.quizapp.model.network.request.user.RegisterRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.user.LoginResponse;
import com.quizcore.quizapp.model.network.response.user.RegistrationResponse;
import com.quizcore.quizapp.model.network.response.user.UserActivityResponse;
import com.quizcore.quizapp.model.network.response.user.UserDetailsResponse;
import com.quizcore.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
//@RequestMapping("api/v1/user")
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

	@GetMapping("/{userId}")
	public BaseResponse<UserDetailsResponse> userDetails(@PathVariable("userId") String userId)
	{
		if(userId == null) {
			ErrorResponse<UserDetailsResponse> response = new ErrorResponse<>("Please provide userId", null);
			return response;
		}
		User user = new User(UUID.fromString(userId));
		User savedUser = userService.getUserById(user);
		if(savedUser != null)
		{
			SuccessResponse<UserDetailsResponse> response = new SuccessResponse<>("User found !!");
			UserDetailsResponse loginResponse = new UserDetailsResponse();
			loginResponse.setEmail(savedUser.email);
			loginResponse.setId(savedUser.id);
			loginResponse.setName(savedUser.getName());
			loginResponse.setMobile(savedUser.getPhone());
			response.data = loginResponse;
			return response;
		}
		else
		{
			ErrorResponse<UserDetailsResponse> response = new ErrorResponse<>("No User found !!", null);
			return response;
		}
	}

	@GetMapping("/{userId}/activity")
	public BaseResponse<UserActivityResponse> userActivity(@PathVariable("userId") String userId){
		if(userId == null) {
			ErrorResponse<UserActivityResponse> response = new ErrorResponse<>("Please provide userId", null);
			return response;
		}
		User user = new User(UUID.fromString(userId));
		User savedUser = userService.getUserById(user);
		if(savedUser != null)
		{
			SuccessResponse<UserActivityResponse> response = new SuccessResponse<>("User found !!");
			UserActivityResponse loginResponse = new UserActivityResponse();
			loginResponse.setEmail(savedUser.email);
			loginResponse.setId(savedUser.id);
			loginResponse.setName(savedUser.getName());
			loginResponse.setMobile(savedUser.getPhone());
			response.data = loginResponse;
			return response;
		}
		else
		{
			ErrorResponse<UserActivityResponse> response = new ErrorResponse<>("No User found !!", null);
			return response;
		}
	}


}

