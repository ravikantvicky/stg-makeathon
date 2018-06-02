package com.stg.makeathon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stg.makeathon.domain.EventServiceRequest;
import com.stg.makeathon.domain.ServiceResponse;
import com.stg.makeathon.entities.Events;
import com.stg.makeathon.services.EventService;

@CrossOrigin
@Controller
public class EventController {
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value="/getEvents", method=RequestMethod.POST)
	public @ResponseBody ServiceResponse getEvents(@RequestBody EventServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		if (request != null) {
			List<Events> eventList = null;
			String searchText = request.getSearchText();
			if (searchText != null && !searchText.trim().isEmpty()) {
				eventList = eventService.getEventsWithoutSearchCriteria(request.getUserId(), request.getLocation());
			} else {
				eventList = eventService.getEventsWithoutSearchCriteria(request.getUserId(), request.getLocation());
			}
			response.setContent(eventList);
			if (eventList.size() > 0) {
				response.setStatus(1);
				response.setMessage("Request successful");
			} else {
				response.setMessage("No result matching search criteria. ");
			}
		} else {
			response.setMessage("Empty Request.");
		}
		return response;
	}
}
