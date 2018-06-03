package com.stg.makeathon.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.stg.makeathon.entities.Events;
import com.stg.makeathon.entities.PrefTopics;
import com.stg.makeathon.entities.UserPrefId;
import com.stg.makeathon.entities.UserPrefrences;
import com.stg.makeathon.exception.TechAdvException;
import com.stg.makeathon.repository.EventsRepository;
import com.stg.makeathon.repository.PrefTopicsRepository;
import com.stg.makeathon.repository.UserPrefRepository;

public class EventServiceImpl implements EventService {

	@Autowired
	private EventsRepository eventRepository;
	@Autowired
	private PrefTopicsRepository prefTopicRepository;
	@Autowired
	private UserPrefRepository userPrefRepository;

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
	public Map<String, List<Events>> search(String userId, String searchText, String location) throws TechAdvException {
		Map<String, List<Events>> result = new HashMap<>();
		List<Events> searchRes = new ArrayList<>();
		try {
			Map<String, String> searchCriteria = getSearchCriteria(searchText);
			if (searchCriteria != null && searchCriteria.containsKey("location")) {
				searchRes = eventRepository.findByKeywordAndLocation("%" + searchCriteria.get("keyword") + "%",
						searchCriteria.get("location"));
			} else {
				searchRes = eventRepository.findByKeyword(searchCriteria.get("keyword"));
			}
			result.put("searchList", searchRes);
			List<Events> recomendation = getEventsWithoutSearchCriteria(userId, location);
			final List<Events> searchResult = searchRes;
			recomendation = recomendation.stream().filter(e -> {
				return !searchResult.contains(e);
			}).collect(Collectors.toList());
			result.put("recomended", recomendation);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TechAdvException(500, "Error in search result.");
		}
		return result;
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

	@Override
	public Map<String, String> getSearchCriteria(String searchText) {
		Map<String, String> result = new HashMap<>();
		if (searchText != null) {
			try {
				String[] res = searchText.split(" ");
				result.put("keyword", res[0]);
				result.put("location", res[1]);
			} catch (Exception e) {
				e.printStackTrace();
				if (!result.containsKey("keyword")) {
					result.put("keyword", "%");
				}
			}
		} else {
			result.put("keyword", "%");
		}
		return result;
	}

	@Override
	public void updateEventClick(String userId, String eventId) throws TechAdvException {
		try {
			Optional<Events> optEvent = eventRepository.findById(Integer.valueOf(eventId));
			if (optEvent.isPresent()) {
				Events event = optEvent.get();
				String[] keywords = event.getKeywords().split(",");
				for (String keyword : keywords) {
					PrefTopics prefTopic = prefTopicRepository.findByPrefTopicIgnoreCase(keyword.trim());
					if (prefTopic == null) {
						prefTopic = new PrefTopics();
						prefTopic.setPrefTopic(keyword.trim());
						prefTopicRepository.saveAndFlush(prefTopic);
					}
					UserPrefId upId = new UserPrefId();
					upId.setPrefId(prefTopic.getPrefId());
					upId.setUserId(Integer.valueOf(userId));
					Optional<UserPrefrences> optUserPref = userPrefRepository.findById(upId);
					UserPrefrences userPref = null;
					if (optUserPref.isPresent()) {
						userPref = optUserPref.get();
					} else {
						userPref = new UserPrefrences();
						userPref.setId(upId);
					}
					userPref.setClickCount((userPref.getClickCount() + 1));
					userPrefRepository.save(userPref);
				}
			} else {
				throw new TechAdvException(404, "Invalid Event Id.");
			}
		} catch (TechAdvException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new TechAdvException(500, e.getMessage());
		}
	}
}
