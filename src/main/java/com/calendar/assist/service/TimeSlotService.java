package com.calendar.assist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.repository.TimeSlotRepository;

@Service
public class TimeSlotService {

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	public TimeSlot saveTimeSlot(final TimeSlot timeSlot) {
		return timeSlotRepository.save(timeSlot);
	}

}
