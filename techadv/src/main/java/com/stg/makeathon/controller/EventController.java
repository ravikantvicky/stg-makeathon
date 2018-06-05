package com.stg.makeathon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stg.makeathon.domain.EventClickRequest;
import com.stg.makeathon.domain.EventServiceRequest;
import com.stg.makeathon.domain.ServiceResponse;
import com.stg.makeathon.entities.Events;
import com.stg.makeathon.exception.TechAdvException;
import com.stg.makeathon.services.EventService;

@CrossOrigin
@Controller
public class EventController {
	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/getEvents", method = RequestMethod.POST)
	public @ResponseBody ServiceResponse getEvents(@RequestBody EventServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		if (request != null) {
			List<Events> eventList = null;
			eventList = eventService.getEventsWithoutSearchCriteria(request.getUserId(), request.getLocation());
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

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody ServiceResponse searchEvents(@RequestBody EventServiceRequest request) {
		try {
			Map<String, List<Events>> searchRes = eventService.search(request.getUserId(), request.getSearchText(),
					request.getLocation());
			return ServiceResponse.getSuccessResponse(searchRes);
		} catch (TechAdvException e) {
			return ServiceResponse.getErrorResponse(e.getErrorMsg());
		} catch (Exception e) {
			return ServiceResponse.getErrorResponse();
		}
	}

	@RequestMapping(value = "/updateEventClick", method = RequestMethod.POST)
	public @ResponseBody ServiceResponse updateEventClick(@RequestBody EventClickRequest request) {
		try {
			eventService.updateEventClick(request.getUserId(), request.getEventId());
			return ServiceResponse.getSuccessResponse("Event Click updated successfully.");
		} catch (TechAdvException e) {
			return ServiceResponse.getErrorResponse(e.getErrorMsg());
		} catch (Exception e) {
			return ServiceResponse.getErrorResponse();
		}
	}

	@RequestMapping(value = "/getSearchKeyword", method = RequestMethod.GET)
	public @ResponseBody ServiceResponse getSearchKeyword() {
		try {
			return ServiceResponse.getSuccessResponse(eventService.getSearchKeyword());
		} catch (TechAdvException e) {
			return ServiceResponse.getErrorResponse(e.getErrorMsg());
		} catch (Exception e) {
			return ServiceResponse.getErrorResponse();
		}
	}
}
