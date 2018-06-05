package com.stg.makeathon.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.stg.makeathon.entities.Events;
import com.stg.makeathon.exception.TechAdvException;

@Service
public interface EventService {

	public List<Events> getEventsWithoutSearchCriteria(String userId, String location);

	public Map<String, List<Events>> search(String userId, String searchText, String location) throws TechAdvException;

	public Map<String, List<String>> getSearchCriteriaForUser(String userId);

	public Map<String, String> getSearchCriteria(String searchText);
	
	public void updateEventClick(String userId, String eventId) throws TechAdvException;
	
	public Set<String> getSearchKeyword() throws TechAdvException;
}
