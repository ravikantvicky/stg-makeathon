package com.stg.makeathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stg.makeathon.domain.LoginRequest;
import com.stg.makeathon.domain.ServiceResponse;
import com.stg.makeathon.domain.SignupRequest;
import com.stg.makeathon.entities.PrefTopics;
import com.stg.makeathon.entities.Users;
import com.stg.makeathon.exception.TechAdvException;
import com.stg.makeathon.services.UserServices;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserServices userService;

	@RequestMapping("/testService")
	public ServiceResponse testService() {
		ServiceResponse response = new ServiceResponse();
		response.setStatus(1);
		response.setMessage("Test Service Successful.");
		return response;
	}

	@RequestMapping("/getAllPrefrences")
	public ServiceResponse getAllPrefrences() {
		try {
			List<PrefTopics> res = userService.getAllPrefTopics();
			return ServiceResponse.getSuccessResponse(res);
		} catch (TechAdvException e) {
			return ServiceResponse.getErrorResponse(e.getMessage());
		}
	}

	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public @ResponseBody ServiceResponse signUp(@RequestBody SignupRequest request) {
		try {
			if (userService.signUp(request)) {
				return ServiceResponse.getSuccessResponse("Registraction successful. Please login to continue.");
			} else {
				return ServiceResponse.getErrorResponse("Unable to complete registration.");
			}
		} catch (TechAdvException e) {
			return ServiceResponse.getErrorResponse(e.getErrorMsg());
		}
	}
	
	@RequestMapping(value="/login")
	public @ResponseBody ServiceResponse login(@RequestBody LoginRequest request) {
		try {
			Users user = userService.login(request);
			return ServiceResponse.getSuccessResponse("Login successful.", user);
		} catch (Exception e) {
			return ServiceResponse.getErrorResponse(e.getMessage());
		}
	}
}
