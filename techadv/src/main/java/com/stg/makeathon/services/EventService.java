package com.stg.makeathon.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stg.makeathon.domain.Events;

@Service
public interface EventService {

	public Map<String, List<String>> getSearchCriteriaForUser(String userId);

	public List<Events> getEventsWithoutSearchCriteria(String userId, String location);
}
