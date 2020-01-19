package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.entity.UserQuizMedia;
import com.quizcore.quizapp.model.network.request.user.LoginRequest;
import com.quizcore.quizapp.model.network.request.user.RegisterRequest;
import com.quizcore.quizapp.model.network.request.user.SubmitVideoRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.user.*;
import com.quizcore.quizapp.model.other.Validity;
import com.quizcore.quizapp.service.UserActivityService;
import com.quizcore.quizapp.service.UserService;
import com.quizcore.quizapp.util.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${base.endpoint}/api/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	UserActivityService userActivityService;
	
	
	@GetMapping("/healthcheck")
	public BaseResponse<Object> checkHealth() {
		SuccessResponse<Object> response = new SuccessResponse<>("It works awesome");
		return response;
	}
	
	@PostMapping("/register")
	public BaseResponse<RegistrationResponse> registerUser(@RequestBody RegisterRequest registermodel)
	{
		Validity requestValidity = registermodel.validate(registermodel);
		if(!requestValidity.isValid()){
			ErrorResponse<RegistrationResponse> response = new ErrorResponse<>(requestValidity.getMessage(), null);
			return response;
	    }

		User user = new User(registermodel.getName(), registermodel.getEmail(), registermodel.getPassword(), registermodel.getPhone(), UUID.fromString(registermodel.getSecret()));
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

	@PostMapping("/{userId}/video")
	public BaseResponse<SubmitVideoResponse> userVideo(@PathVariable("userId") String userId, @RequestBody SubmitVideoRequest submitVideoRequest){
		if(submitVideoRequest.getQuizId() == null || submitVideoRequest.getVideoId() == null) {
			ErrorResponse<SubmitVideoResponse> response = new ErrorResponse<>("Request incomplete", null);
			return response;
		}
		User user = new User(UUID.fromString(userId));
		User savedUser = userService.getUserById(user);
		if(savedUser != null)
		{
			SuccessResponse<SubmitVideoResponse> response = new SuccessResponse<>("Video saved !!");
			UserQuizMedia userProductMedia = new UserQuizMedia(savedUser.id, UUID.fromString(submitVideoRequest.getQuizId()),UUID.fromString(submitVideoRequest.getVideoId()),"video");
			UUID mediaId = userService.saveUserMedia(userProductMedia);
			userActivityService.saveUserQuizAction(UUID.fromString(userId),UUID.fromString(submitVideoRequest.getQuizId()), UserAction.UPLOAD_VIDEO);
			SubmitVideoResponse submitVideoResponse = new SubmitVideoResponse();
			submitVideoResponse.setStoredMediaId(mediaId.toString());
			response.data = submitVideoResponse;
			return response;
		}
		else
		{
			ErrorResponse<SubmitVideoResponse> response = new ErrorResponse<>("No User found !!", null);
			return response;
		}
	}

}

