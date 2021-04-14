package com.calendar.assist.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.calendar.assist.dto.MeetingDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.exception.CalendarAssistBusinessException;
import com.calendar.assist.mock.service.MeetingMockService;
import com.calendar.assist.repository.MeetingRepository;

@RunWith(MockitoJUnitRunner.class)
public class MeetingServiceTest {

	@InjectMocks
	private MeetingService meetingService;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private CalendarService calendarService;

	@Mock
	private TimeSlotService timeSlotService;

	@Mock
	private InvitationService invitationService;

	@Mock
	private MeetingRepository meetingRepository;

	@Test
	public void testBookMeeting() {
		MeetingDto meetingDto = MeetingMockService.mockMeetingDto();

		when(employeeService.getEmployeeByEmailId(anyString())).thenReturn(MeetingMockService.mockEmployee());

		when(meetingRepository.save(any(Meeting.class))).thenReturn(MeetingMockService.mockMeeting());

		when(calendarService.saveCalendar(any(Calendar.class))).thenReturn(MeetingMockService.mockCalendar());

		assertDoesNotThrow(() -> meetingService.bookMeeting(meetingDto));
	}

}
