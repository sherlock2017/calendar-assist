package com.calendar.assist.service;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.SlotStatus;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.repository.CalendarRepository;

@Service
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepository;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	
	/**
	 * this service API saves the calendar and blocks the timeslot
	 * 
	 * @param calendar
	 */
	public void saveCalendar(Calendar calendar, final Meeting meeting) {
		
		calendar = calendarRepository.save(calendar);
		
		LocalTime startTime = meeting.getStartDateTime().toLocalTime();
		LocalTime endTime = meeting.getEndDateTime().toLocalTime();
		
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setCalendarId(calendar.getCalendarId());
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setSlotStatus(SlotStatus.BOOKED);
		timeSlotService.saveTimeSlot(timeSlot);
	}

}
