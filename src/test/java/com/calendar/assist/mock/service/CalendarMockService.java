package com.calendar.assist.mock.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assists.enums.SlotStatus;

public class CalendarMockService {

	public static BigInteger empId1 = BigInteger.ONE;

	public static BigInteger empId2 = BigInteger.TEN;

	public static LocalDate calendarDate = LocalDate.now();

	public static LocalTime startTime = LocalTime.now();

	public static LocalTime endTime = LocalTime.now().plusMinutes(30);

	public static int durationInMins = 30;

	public static Calendar mockCalendar() {
		return new Calendar(BigInteger.ONE, BigInteger.TEN, LocalDate.now());
	}

	public static Calendar mockAnotherCalendar() {
		return new Calendar(BigInteger.TEN, BigInteger.ONE, LocalDate.now());
	}

	public static List<TimeSlot> mockBookedTimeSlots() {
		return Stream.of(new TimeSlot(BigInteger.ONE, BigInteger.TEN, LocalTime.now(), LocalTime.now().plusHours(1),
				SlotStatus.BOOKED)).collect(Collectors.toList());
	}

	public static EmployeeDto mockEmployeeDto() {
		return new EmployeeDto("rishabh.jain@gmail.com");
	}

}
