package com.calendar.assist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.dto.MeetingDto;
import com.calendar.assist.service.MeetingService;

/**
 * Controller to handle meetings
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@RestController
@RequestMapping(value = "/meeting/v1/")
public class MeetingController {

	/**
	 * service to handle meetings logic an operations
	 */
	@Autowired
	private MeetingService meetingService;
	
	/**
	 * This API book a meeting for an employee
	 * 
	 * @param meetingDto
	 */
	@PostMapping("book")
	public void bookMeeting(@RequestBody final MeetingDto meetingDto) {
		meetingService.bookMeeting(meetingDto);
	}
	
	
	/**
	 * This API finds the participants who find conflicts for a meeting
	 * 
	 * @param meetingDto
	 * @return
	 */
	@PostMapping("conflicts")
	public List<EmployeeDto> getConflictedParticipants(@RequestBody final MeetingDto meetingDto){
		return meetingService.getConflictedParticipants(meetingDto);
	}
}
