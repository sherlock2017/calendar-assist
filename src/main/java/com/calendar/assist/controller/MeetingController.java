package com.calendar.assist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.assist.dto.MeetingDto;
import com.calendar.assist.entity.Employee;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.service.MeetingService;

@RestController
@RequestMapping(value = "/meeting/v1/")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;
	
	@GetMapping("greet")
	public String getGreeting() {
		return "Welcome to Calendar Assist";
	}

	@PostMapping("book")
	public void bookMeeting(@RequestBody final MeetingDto meetingDto) {
		meetingService.bookMeeting(meetingDto);
	}

	@GetMapping("free-slots/{emp1}/{emp2}")
	public List<TimeSlot> getFreeTimeSlots(@PathVariable("emp1") final String emp1,
			@PathVariable("emp2") final String emp2) {
		return meetingService.getFreeTimeSlots(emp1, emp2);
	}
	
	@PostMapping("conflicts")
	public List<Employee> getConflictedParticipants(@RequestBody final Meeting meeting){
		return meetingService.getConflictedParticipants(meeting);
	}
}
