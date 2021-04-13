package com.calendar.assist.service;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.dto.TimeSlotDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.SlotStatus;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.repository.CalendarRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepository;

	@Autowired
	private TimeSlotService timeSlotService;

	/**
	 * this service API saves the calendar and blocks the timeslot
	 * 
	 * @param calendar
	 */
	public void saveCalendar(Calendar calendar, final Meeting meeting) {

		calendar = calendarRepository.save(calendar);

		LocalTime startTime = meeting.getStartDateTime().toLocalTime();
		LocalTime endTime = meeting.getEndDateTime().toLocalTime();

		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setCalendarId(calendar.getCalendarId());
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setSlotStatus(SlotStatus.BOOKED);
		timeSlotService.saveTimeSlot(timeSlot);
	}

	public LinkedHashSet<TimeSlotDto> getFreeTimeSlots(BigInteger empId1, BigInteger empId2, LocalDate calendarDate,
			int durationInMins) {

		// get calendar Ids for both emps and TimeSlots for each calendar
		BigInteger calendarId1 = calendarRepository.getCalendarIdForEmployee(empId1, calendarDate);
		BigInteger calendarId2 = calendarRepository.getCalendarIdForEmployee(empId2, calendarDate);

		//Duration duration = Duration.ofMinutes(durationInMins);

		List<TimeSlot> timeSlotsForEmployee1 = timeSlotService.getBookedTimeSlots(calendarId1);
		List<TimeSlot> timeSlotsForEmployee2 = timeSlotService.getBookedTimeSlots(calendarId2);
		List<TimeSlot> bookedTimeSlots = Stream
				.concat(timeSlotsForEmployee1.parallelStream(), timeSlotsForEmployee2.parallelStream())
				.collect(Collectors.toList());

		log.info("Booked Slots for employee {}: {}", empId1, timeSlotsForEmployee1.toString());
		log.info("Booked Slots for employee {}: {}", empId2, timeSlotsForEmployee2.toString());

		LinkedHashSet<TimeSlotDto> availableSlots = computeFreeSlots(bookedTimeSlots, durationInMins);

		// derive the free time slots using booked time slots
		return availableSlots;
	}

	private LinkedHashSet<TimeSlotDto> computeFreeSlots(List<TimeSlot> bookedTimeSlots, int duration) {

		LinkedHashSet<TimeSlotDto> availableSlots = new LinkedHashSet<>();
		bookedTimeSlots.forEach(bookedSlot -> {
			LocalTime startTime = LocalTime.parse("00:00");
			LocalTime endTime = LocalTime.parse("00:30");
			LocalTime bookedStartTime = bookedSlot.getStartTime();
			LocalTime bookedEndTime = bookedSlot.getEndTime();

			while (!startTime.equals(LocalTime.parse("23:30")) && !endTime.equals(LocalTime.parse("00:00"))) {
				if((startTime.isBefore(bookedStartTime) && startTime.isBefore(bookedEndTime) && endTime.isAfter(bookedStartTime) && endTime.isBefore(bookedEndTime)) || 
						(startTime.isAfter(bookedStartTime) && startTime.isBefore(bookedEndTime) && endTime.isAfter(bookedStartTime) && endTime.isBefore(bookedEndTime)) ||
						(startTime.isAfter(bookedStartTime) && startTime.isBefore(bookedEndTime) && endTime.isAfter(bookedStartTime) && endTime.isAfter(bookedEndTime)) ||
						(startTime.equals(bookedStartTime) ||  endTime.equals(bookedEndTime))) {
					log.info("proposed timeslot conflicts - ({},{}) - ({},{})", startTime, endTime, bookedStartTime, bookedEndTime);
				}
				else {
					TimeSlotDto ts = new TimeSlotDto();
					ts.setStartTime(startTime);
					ts.setEndTime(endTime);
					ts.setSlotStatus(SlotStatus.AVAILABLE);
					availableSlots.add(ts);
					log.info("found free timeslot - ({},{})", startTime, endTime);
				}
				startTime = startTime.plusMinutes(duration);
				endTime = endTime.plusMinutes(duration);
			}
		});
		
		return availableSlots;
	}

}
