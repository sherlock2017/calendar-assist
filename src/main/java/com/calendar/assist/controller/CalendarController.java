package com.calendar.assist.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.assist.dto.TimeSlotDto;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.service.CalendarService;

@RestController
@RequestMapping(value = "/calendar/v1/")
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

	@GetMapping("free-slots/{emp1}/{emp2}/{calendarDate}/{durationInMins}")
	public LinkedHashSet<TimeSlotDto> getFreeTimeSlots(@PathVariable("emp1") final BigInteger emp1,
			@PathVariable("emp2") final BigInteger emp2, @PathVariable("calendarDate") @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate calendarDate,
			@PathVariable("durationInMins") final int durationInMins) {
		return calendarService.getFreeTimeSlots(emp1, emp2, calendarDate, durationInMins);
	}
}
