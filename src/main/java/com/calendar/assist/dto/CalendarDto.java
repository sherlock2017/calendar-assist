package com.calendar.assist.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarDto {

	private LocalDate date;
	private TimeSlotDto timeSlot;

}
