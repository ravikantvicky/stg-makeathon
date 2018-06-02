package com.stg.makeathon.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.stg.makeathon.entities.Events;
import com.stg.makeathon.repository.EventsRepository;

public class EventServiceImpl implements EventService {

	@Autowired
	private EventsRepository eventRepository;

	@Override
	public List<Events> getEventsWithoutSearchCriteria(String userId, String location) {
		List<Events> result = new ArrayList<>();

		Map<String, List<String>> searchCriteria = getSearchCriteriaForUser(userId);
		List<String> keywords = searchCriteria.get("keywords");
		List<String> locations = searchCriteria.get("locations");
		if (location != null && !location.trim().isEmpty()) {
			locations.add(location);
		}

		if (keywords.size() > 0 && locations.size() > 0) {
			keywords.forEach(keyword -> {
				locations.forEach(loc -> {
					result.addAll(eventRepository.findByKeywordAndLocation("%" + keyword + "%", loc));
				});
			});
		}
		if (keywords.size() > 0) {
			keywords.forEach(keyword -> {
				result.addAll(eventRepository.findByKeyword(keyword));
			});
		}
		if (locations.size() > 0) {
			locations.forEach(loc -> {
				result.addAll(eventRepository.findByKeywordAndLocation("%", loc));
			});
		}

		if (result.size() == 0) {
			result.addAll(eventRepository.findUpcomingEvents());
		}

		return result.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public Map<String, List<String>> getSearchCriteriaForUser(String userId) {
		List<String> keywords = new ArrayList<>();
		List<String> locations = new ArrayList<>();

		if (userId != null) {
			keywords.add("java");
			locations.add("Bhubaneswar");
		}

		Map<String, List<String>> result = new HashMap<>();
		result.put("keywords", keywords);
		result.put("locations", locations);
		return result;
	}

}
