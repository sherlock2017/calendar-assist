package com.calendar.assist.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.assist.dto.TimeSlotDto;
import com.calendar.assist.service.CalendarService;

/**
 * Controller to handle calendar
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@RestController
@RequestMapping(value = "/calendar/v1/")
public class CalendarController {

	/**
	 * service to handle calendar logics and operations
	 */
	@Autowired
	private CalendarService calendarService;

	/**
	 * This API find available time slots between 2 employees on a calendar date
	 * 
	 * @param emp1
	 * @param emp2
	 * @param calendarDate
	 * @param durationInMins
	 * @return
	 */
	@GetMapping("available-slots")
	public Set<TimeSlotDto> getAvailableSlots(@PathParam("empId1") final BigInteger empId1,
			@PathParam("empId2") final BigInteger empId2, @PathParam("calendarDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate calendarDate,
			@PathParam("durationInMins") final int durationInMins) {
		return calendarService.getAvailableSlots(empId1, empId2, calendarDate, durationInMins);
	}
}
