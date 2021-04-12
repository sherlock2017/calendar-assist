package com.calendar.assist.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

	private LocalTime startTime;
	private LocalTime endTime;
	private SlotStatus slotStatus;
	
}
