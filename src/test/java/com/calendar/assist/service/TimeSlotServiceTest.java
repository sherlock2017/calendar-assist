package com.calendar.assist.service;

import static org.mockito.Mockito.when;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.h2.command.dml.MergeUsing.When;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.mock.service.TimeSlotMockService;
import com.calendar.assist.repository.TimeSlotRepository;

@RunWith(MockitoJUnitRunner.class)
public class TimeSlotServiceTest {

	@InjectMocks
	private TimeSlotService timeSlotService;

	@Mock
	private TimeSlotRepository timeSlotRepository;

	@Test
	public void testSaveTimeSlot() {
		TimeSlot timeSlot = TimeSlotMockService.mockTimeSlot();

		when(timeSlotRepository.save(any(TimeSlot.class))).thenReturn(TimeSlotMockService.mockBookedTimeSlot());
		
		TimeSlot bookedTimeSlot = timeSlotService.saveTimeSlot(timeSlot);
		
		assertNotNull(bookedTimeSlot);
		assertEquals(BigInteger.ONE, bookedTimeSlot.getSlotId());
		
	}
}
