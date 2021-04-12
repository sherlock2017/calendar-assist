package com.calendar.assist.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSlotDto {

	private LocalTime startTime;
	private LocalTime endTime;
	private SlotStatusDto slotStatus;
	
	
}
