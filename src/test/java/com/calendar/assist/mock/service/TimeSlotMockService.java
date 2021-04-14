package com.calendar.assist.mock.service;

import java.math.BigInteger;
import java.time.LocalTime;

import com.calendar.assist.entity.TimeSlot;
import com.calendar.assists.enums.SlotStatus;

public class TimeSlotMockService {

	public static TimeSlot mockTimeSlot() {
		return new TimeSlot(null, BigInteger.TEN, LocalTime.now(), LocalTime.now().plusMinutes(10),
				SlotStatus.BOOKED);
	}
	
	public static TimeSlot mockBookedTimeSlot() {
		return new TimeSlot(BigInteger.ONE, BigInteger.TEN, LocalTime.now(), LocalTime.now().plusMinutes(10),
				SlotStatus.BOOKED);
	}
}
