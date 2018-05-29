package com.stg.makeathon.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stg.makeathon.domain.ServiceResponse;

@CrossOrigin
@RestController
public class UserController {

	@RequestMapping("/testService")
	public ServiceResponse testService() {
		ServiceResponse response = new ServiceResponse();
		response.setStatus(1);
		response.setMessage("Test Service Successful.");
		return response;
	}
}
