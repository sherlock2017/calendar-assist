package com.calendar.assist.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.dto.MeetingDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Employee;
import com.calendar.assist.entity.Invitation;
import com.calendar.assist.entity.InviteStatus;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.SlotStatus;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.repository.MeetingRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeetingService {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CalendarService calendarService;
	
	@Autowired
	private InvitationService invitationService;
	
	@Autowired
	private MeetingRepository meetingRepository;
	
	
	@Transactional
	public void bookMeeting(final MeetingDto meetingDto) {
		ArrayList<EmployeeDto> atendees = meetingDto.getAtendees();
		
		Meeting meeting = saveMeeting(meetingDto);
		
		if(Optional.ofNullable(atendees).isPresent() && !atendees.isEmpty()) {
			
			EmployeeDto organizer = meetingDto.getOrganizer();
			LocalDateTime meetinStartDateTime = meetingDto.getStartDateTime();
			LocalDateTime meetinEndDateTime = meetingDto.getEndDateTime();
			
			
			atendees.forEach(atendee -> {
				Employee employee = employeeService.getEmployeeById(atendee.getEmailId());
				if(Optional.ofNullable(employee).isPresent()) {
					log.error("Valid atendee: {}", atendee.getEmailId());
					
					//check if employee has that time slot available
					
					
					// if yes then create invite
					Invitation invitation = new Invitation();
					invitation.setEmployeeId(employee.getEmployeeId());
					invitation.setMeetingId(meeting.getMeetingId());
					invitation.setInviteStatus(InviteStatus.NOT_ACCEPTED);
					invitationService.sendInvite(invitation);
					
					// add the meeting to person calendar
					Calendar calendar = new Calendar();
					calendar.setEmployeeId(employee.getEmployeeId());
					calendar.setDate(meeting.getStartDateTime().toLocalDate());
					calendarService.saveCalendar(calendar, meeting);
				}
				else {
					log.error("Invalid atendee: {}", atendee.getEmailId());
				}
			});
		}
	}

	/**
	 * save the meeting using meeting repository
	 * 
	 * @param meetingDto
	 * @return
	 */
	private Meeting saveMeeting(final MeetingDto meetingDto) {
		Meeting meeting = new Meeting();
		BeanUtils.copyProperties(meetingDto, meeting);
		meeting.setOrganizerId(employeeService.getEmployeeById(meetingDto.getOrganizer().getEmailId()).getEmployeeId());
		return meetingRepository.save(meeting);
	}

	public List<TimeSlot> getFreeTimeSlots(String emp1, String emp2) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> getConflictedParticipants(Meeting meeting) {
		
		// get the meeting sta
		
		return null;
	}

}
