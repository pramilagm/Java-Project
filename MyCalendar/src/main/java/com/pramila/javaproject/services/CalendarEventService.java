package com.pramila.javaproject.services;

import java.util.List;



import org.springframework.stereotype.Service;

import com.pramila.javaproject.model.Calendar;
import com.pramila.javaproject.repositories.CalendarEventRepository;

@Service
public class CalendarEventService {
	
	private final CalendarEventRepository eventRepo;

	public CalendarEventService(CalendarEventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}

	public List<Calendar> finaAllEvents() {
		return eventRepo.findAll();
	}

	public Calendar createTask(Calendar event) {
		return eventRepo.save(event);
		
		
	}

	public Calendar findEventById(Long eventId) {
		return eventRepo.findById(eventId).orElse(null);
	}
	
	

}
