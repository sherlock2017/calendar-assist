package com.calendar.assist.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.dto.TimeSlotDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Employee;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.repository.CalendarRepository;
import com.calendar.assists.enums.SlotStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * Service to operate on Calendar
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Service
@Slf4j
public class CalendarService {

	@Autowired
	private CalendarRepository calendarRepository;

	@Autowired
	private TimeSlotService timeSlotService;

	@Autowired
	private EmployeeService employeeService;

	/**
	 * this service API saves the calendar and blocks the timeslot
	 * 
	 * @param calendar
	 */
	public Calendar saveCalendar(Calendar calendar, final Meeting meeting) {
		return calendarRepository.save(calendar);
	}

	/**
	 * @param empId1
	 * @param empId2
	 * @param calendarDate
	 * @param durationInMins
	 * @return
	 */
	public Set<TimeSlotDto> getAvailableSlots(BigInteger empId1, BigInteger empId2, LocalDate calendarDate,
			int durationInMins) {

		// get calendar Ids for both emps and TimeSlots for each calendar
		BigInteger calendarId1 = calendarRepository.getCalendarIdForEmployee(empId1, calendarDate);
		BigInteger calendarId2 = calendarRepository.getCalendarIdForEmployee(empId2, calendarDate);

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

	/**
	 * @param bookedTimeSlots
	 * @param duration
	 * @return
	 */
	private LinkedHashSet<TimeSlotDto> computeFreeSlots(List<TimeSlot> bookedTimeSlots, int duration) {

		LinkedHashSet<TimeSlotDto> availableSlots = new LinkedHashSet<>();
		bookedTimeSlots.forEach(bookedSlot -> {
			LocalTime proposedStartTime = LocalTime.parse("00:00");
			LocalTime proposedEndTime = LocalTime.parse("00:30");
			LocalTime bookedStartTime = bookedSlot.getStartTime();
			LocalTime bookedEndTime = bookedSlot.getEndTime();

			while (!proposedStartTime.equals(LocalTime.parse("23:30"))
					&& !proposedEndTime.equals(LocalTime.parse("00:00"))) {
				if (computeConflicts(proposedStartTime, proposedEndTime, bookedStartTime, bookedEndTime)) {
					log.info("proposed timeslot conflicts - ({},{}) - ({},{})", proposedStartTime, proposedEndTime,
							bookedStartTime, bookedEndTime);
				} else {
					TimeSlotDto ts = new TimeSlotDto();
					ts.setStartTime(proposedStartTime);
					ts.setEndTime(proposedEndTime);
					ts.setSlotStatus(SlotStatus.AVAILABLE);
					availableSlots.add(ts);
				}
				proposedStartTime = proposedStartTime.plusMinutes(duration);
				proposedEndTime = proposedEndTime.plusMinutes(duration);
			}
		});

		return availableSlots;
	}

	/**
	 * @param atendee
	 * @param meetingDate
	 * @param meetingStartTime
	 * @param meetingEndTime
	 * @return
	 */
	public boolean checkAnyConflictExist(EmployeeDto atendee, LocalDate meetingDate, LocalTime meetingStartTime,
			LocalTime meetingEndTime) {

		final Employee employee = employeeService.getEmployeeByEmailId(atendee.getEmailId());

		// get calender id of the atendee for a specefic date
		final BigInteger calendarId = getCalendarIdForEmployee(employee.getEmployeeId(), meetingDate);

		List<TimeSlot> timeSlots = timeSlotService.getBookedTimeSlots(calendarId);
		AtomicBoolean conflictExists = new AtomicBoolean(Boolean.FALSE);
		timeSlots.forEach(timeSlot -> {
			final LocalTime bookedStartTime = timeSlot.getStartTime();
			final LocalTime bookedEndTime = timeSlot.getEndTime();
			conflictExists.set(computeConflicts(meetingStartTime, meetingEndTime, bookedStartTime, bookedEndTime));
			if (Boolean.TRUE.equals(conflictExists.get()))
				return;
		});

		return conflictExists.get();
	}

	/**
	 * @param proposedStartTime
	 * @param proposedEndTime
	 * @param bookedStartTime
	 * @param bookedEndTime
	 * @return
	 */
	private boolean computeConflicts(LocalTime proposedStartTime, LocalTime proposedEndTime, LocalTime bookedStartTime,
			LocalTime bookedEndTime) {

		boolean conflictExists = false;
		if ((proposedStartTime.isBefore(bookedStartTime) && proposedStartTime.isBefore(bookedEndTime)
				&& proposedEndTime.isAfter(bookedStartTime) && proposedEndTime.isBefore(bookedEndTime))
				|| (proposedStartTime.isAfter(bookedStartTime) && proposedStartTime.isBefore(bookedEndTime)
						&& proposedEndTime.isAfter(bookedStartTime) && proposedEndTime.isBefore(bookedEndTime))
				|| (proposedStartTime.isAfter(bookedStartTime) && proposedStartTime.isBefore(bookedEndTime)
						&& proposedEndTime.isAfter(bookedStartTime) && proposedEndTime.isAfter(bookedEndTime))
				|| (proposedStartTime.equals(bookedStartTime) && proposedEndTime.isBefore(bookedEndTime))
				|| (proposedEndTime.isAfter(bookedStartTime) && proposedEndTime.equals(bookedEndTime))) {
			conflictExists = true;
		}

		return conflictExists;
	}

	public BigInteger getCalendarIdForEmployee(final BigInteger employeeId, final LocalDate calendarDate) {
		return calendarRepository.getCalendarIdForEmployee(employeeId, calendarDate);
	}
}
