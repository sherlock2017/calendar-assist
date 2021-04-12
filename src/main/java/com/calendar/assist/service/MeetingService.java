package com.calendar.assist.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.dto.MeetingDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Employee;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.SlotStatus;
import com.calendar.assist.entity.TimeSlot;


import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeetingService {

	@Autowired
	private EmployeeService employeeService;
	
	private ArrayList<Employee> employeesIntoMeeting = new ArrayList<>();
	private ArrayList<Meeting> meetingList = new ArrayList<>();
	
	public void bookMeeting(final MeetingDto meetingDto) {
		ArrayList<EmployeeDto> atendees = meetingDto.getAtendees();
		if(Optional.ofNullable(atendees).isPresent() && !atendees.isEmpty()) {
			atendees.forEach(atendee -> {
				Optional<Employee> emp = employeeService.getEmployeeById(atendee.getEmailId());
				if(emp.isPresent()) {
					log.info("Employee -- {} ", emp.get().toString());
					Employee employee = emp.get();
					
					// then save
					LocalDateTime meetingStartDateTime = meetingDto.getStartDateTime();
					LocalDateTime meetingEndDateTime = meetingDto.getEndDateTime();
					
					Calendar calendar = new Calendar();
					calendar.setDate(meetingStartDateTime.toLocalDate());
					
					TimeSlot timeSlot = new TimeSlot();
					timeSlot.setStartTime(meetingStartDateTime.toLocalTime());
					timeSlot.setEndTime(meetingEndDateTime.toLocalTime());
					timeSlot.setSlotStatus(SlotStatus.BOOKED);
					calendar.setTimeSlot(timeSlot);
					employee.setCalendar(calendar);
					
					employeesIntoMeeting.add(employee);
				}
				
				log.info(employeesIntoMeeting.toString());
				
			});
		}
	}

	public List<TimeSlot> getFreeTimeSlots(String emp1, String emp2) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> getConflictedParticipants(Meeting meeting) {
		// TODO Auto-generated method stub
		return null;
	}

}
