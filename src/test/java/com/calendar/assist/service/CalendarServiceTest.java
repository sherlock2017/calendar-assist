package com.calendar.assist.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.awt.print.Book;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.dto.TimeSlotDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Employee;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.mock.service.CalendarMockService;
import com.calendar.assist.mock.service.MeetingMockService;
import com.calendar.assist.repository.CalendarRepository;

@RunWith(MockitoJUnitRunner.class)
public class CalendarServiceTest {

	@InjectMocks
	private CalendarService caledarService;

	@Mock
	private CalendarRepository calendarRepository;

	@Mock
	private TimeSlotService timeSlotService;

	@Mock
	private EmployeeService employeeService;

	@Test
	public void testSaveCalendar() {
		Calendar calendar = CalendarMockService.mockCalendar();

		when(calendarRepository.save(any(Calendar.class))).thenReturn(CalendarMockService.mockCalendar());

		Calendar savedCalendar = caledarService.saveCalendar(calendar);

		assertNotEquals(calendar, savedCalendar);
		assertNotNull(savedCalendar.getCalendarId());
	}

	@Test
	public void testGetAvailableSlots() {
		BigInteger empId1 = CalendarMockService.empId1;
		BigInteger empId2 = CalendarMockService.empId2;
		LocalDate calendarDate = CalendarMockService.calendarDate;
		int durationInMins = CalendarMockService.durationInMins;

		Set<TimeSlotDto> availableTimeSlotSet = caledarService.getAvailableSlots(empId1, empId2, calendarDate,
				durationInMins);

		assertNotNull(availableTimeSlotSet);
	}

	@Test
	public void testCheckAnyConflictExist() {
		EmployeeDto atendee = CalendarMockService.mockEmployeeDto();
		LocalDate meetingDate = CalendarMockService.calendarDate;
		LocalTime meetingStartTime = CalendarMockService.startTime;
		LocalTime meetingEndTime = CalendarMockService.endTime;

		when(employeeService.getEmployeeByEmailId(anyString())).thenReturn(MeetingMockService.mockEmployee());

		Boolean conflictExist = caledarService.checkAnyConflictExist(atendee, meetingDate, meetingStartTime,
				meetingEndTime);

		assertNotNull(conflictExist);
	}
}
