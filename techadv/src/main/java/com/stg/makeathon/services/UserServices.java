package com.stg.makeathon.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stg.makeathon.domain.SignupRequest;
import com.stg.makeathon.entities.PrefTopics;
import com.stg.makeathon.exception.TechAdvException;

@Service
public interface UserServices {
	public List<PrefTopics> getAllPrefTopics() throws TechAdvException;
	public boolean signUp(SignupRequest request) throws TechAdvException;
}
