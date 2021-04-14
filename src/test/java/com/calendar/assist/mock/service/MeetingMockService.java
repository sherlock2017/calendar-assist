package com.calendar.assist.mock.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.dto.MeetingDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Employee;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assists.enums.SlotStatus;

public class MeetingMockService {

	public static MeetingDto mockMeetingDto() {
		return new MeetingDto("Jnunit Testing Discussion", LocalDateTime.now(), LocalDateTime.now().plusHours(1),
				(ArrayList<EmployeeDto>) Stream
						.of(new EmployeeDto("megha.jain@gmail.com"), new EmployeeDto("barney@gmail.com"))
						.collect(Collectors.toList()),
				"Junit Testing discussion", new EmployeeDto("rishabh.jain@gmail.com"));
	}
	
	public static MeetingDto mockMeetingClashDto() {
		return new MeetingDto("Jnunit Testing Discussion", null, null,
				(ArrayList<EmployeeDto>) Stream
						.of(new EmployeeDto("megha.jain@gmail.com"), new EmployeeDto("barney@gmail.com"))
						.collect(Collectors.toList()),
				"Junit Testing discussion", new EmployeeDto("rishabh.jain@gmail.com"));
	}

	public static Employee mockEmployee() {
		return new Employee(BigInteger.ONE, "Rishabh Jain", "rishabh.jain@gmail.com");
	}

	public static Meeting mockMeeting() {
		MeetingDto meetingDto = mockMeetingDto();
		Meeting meeting = new Meeting();
		BeanUtils.copyProperties(meetingDto, meeting);
		meeting.setMeetingId(BigInteger.TEN);
		return meeting;

	}

	public static TimeSlot mockTimeSlot() {
		return new TimeSlot(BigInteger.ONE, BigInteger.TEN, LocalTime.now(), LocalTime.now(), SlotStatus.BOOKED);
	}

	public static Calendar mockCalendar() {
		return new Calendar(BigInteger.ONE, BigInteger.TEN, LocalDate.now());
	}
}
