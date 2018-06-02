package com.stg.makeathon.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.stg.makeathon.domain.SignupRequest;
import com.stg.makeathon.entities.PrefTopics;
import com.stg.makeathon.entities.UserPrefId;
import com.stg.makeathon.entities.UserPrefrences;
import com.stg.makeathon.entities.Users;
import com.stg.makeathon.exception.TechAdvException;
import com.stg.makeathon.repository.PrefTopicsRepository;
import com.stg.makeathon.repository.UserPrefRepository;
import com.stg.makeathon.repository.UsersRepository;
import com.stg.makeathon.util.CommonUtil;

public class UserServicesImpl implements UserServices {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PrefTopicsRepository prefTopicRepository;
	@Autowired
	private UserPrefRepository userPrefRepository;

	@Override
	public List<PrefTopics> getAllPrefTopics() throws TechAdvException {
		try {
			return prefTopicRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TechAdvException(500, e.getMessage());
		}
	}

	@Override
	public boolean signUp(SignupRequest request) throws TechAdvException {
		try {
			Users user = CommonUtil.mapUserRequest(request);
			if (usersRepository.saveAndFlush(user) != null) {
				List<UserPrefrences> prefrences = new ArrayList<>();
				request.getPrefrences().forEach(prefId -> {
					UserPrefrences pref = new UserPrefrences();
					pref.setId(new UserPrefId(user.getUserId(), Integer.valueOf(prefId)));
					prefrences.add(pref);
				});
				userPrefRepository.saveAll(prefrences);
				return true;
			} else {
				throw new TechAdvException(500, "Unable to insert user details.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new TechAdvException(500, e.getMessage());
		}
	}
}
