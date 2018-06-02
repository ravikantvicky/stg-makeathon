package com.stg.makeathon.util;

import com.stg.makeathon.domain.SignupRequest;
import com.stg.makeathon.entities.Users;

public class CommonUtil {
	public static Users mapUserRequest(SignupRequest request) {
		Users user = new Users();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setPassword(request.getPassword());
		return user;
	}
}
