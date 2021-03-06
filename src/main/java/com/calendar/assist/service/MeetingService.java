package com.calendar.assist.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.dto.MeetingDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Employee;
import com.calendar.assist.entity.Invitation;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.entity.TimeSlot;
import com.calendar.assist.exception.CalendarAssistBusinessException;
import com.calendar.assist.exception.ErrorDetail;
import com.calendar.assist.repository.MeetingRepository;
import com.calendar.assists.enums.InviteStatus;
import com.calendar.assists.enums.SlotStatus;

import lombok.extern.slf4j.Slf4j;

/**
 * Service to operate on Meeting
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Service
@Slf4j
public class MeetingService {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CalendarService calendarService;

	@Autowired
	private TimeSlotService timeSlotService;

	@Autowired
	private InvitationService invitationService;

	@Autowired
	private MeetingRepository meetingRepository;

	/**
	 * @param meetingDto
	 */
	@Transactional
	public void bookMeeting(final MeetingDto meetingDto) {

		ArrayList<EmployeeDto> atendees = meetingDto.getAtendees();

		// check if meeting already organized by this organized at same time
		Employee organizer = employeeService.getEmployeeByEmailId(meetingDto.getOrganizer().getEmailId());
		final boolean similarMeetingExists = checkIfSimilarMeetingExists(meetingDto, organizer);

		if (!similarMeetingExists) {

			Meeting meeting = saveMeeting(meetingDto);
			addMeetingToCalendar(meeting, organizer);

			if (Optional.ofNullable(atendees).isPresent() && !atendees.isEmpty()) {
				atendees.forEach(atendee -> {
					Employee employee = employeeService.getEmployeeByEmailId(atendee.getEmailId());
					if (Optional.ofNullable(employee).isPresent()) {
						log.error("Valid atendee: {}", atendee.getEmailId());

						// if yes then create invite
						Invitation invitation = new Invitation();
						invitation.setEmployeeId(employee.getEmployeeId());
						invitation.setMeetingId(meeting.getMeetingId());
						invitation.setInviteStatus(InviteStatus.NOT_ACCEPTED);
						invitationService.sendInvite(invitation);

						addMeetingToCalendar(meeting, employee);
					} else {
						log.error("Invalid atendee: {}", atendee.getEmailId());
					}
				});
			}
		} else {
			final ErrorDetail errorDetails = new ErrorDetail("CA101",
					"Sorry! You have booked a same meeting at this time slot");
			throw new CalendarAssistBusinessException(errorDetails);
		}
	}

	/**
	 * @param meetingDto
	 * @param organizer
	 * @return
	 */
	private boolean checkIfSimilarMeetingExists(MeetingDto meetingDto, Employee organizer) {

		boolean similarMeetingExists = false;
		BigInteger organizerId = organizer.getEmployeeId();
		LocalDateTime meetingStartTime = meetingDto.getStartDateTime();
		LocalDateTime meetingEndTime = meetingDto.getEndDateTime();

		Meeting meeting = meetingRepository.getMeeting(organizerId, meetingStartTime, meetingEndTime);

		if (Optional.ofNullable(meeting).isPresent()) {
			similarMeetingExists = true;
		}
		return similarMeetingExists;
	}

	/**
	 * @param meeting
	 * @param employee
	 */
	private void addMeetingToCalendar(Meeting meeting, Employee employee) {

		// check if same date calendar already exists, if not then create one
		final TimeSlot timeSlot = new TimeSlot();
		final LocalTime startTime = meeting.getStartDateTime().toLocalTime();
		final LocalTime endTime = meeting.getEndDateTime().toLocalTime();
		final BigInteger existingCalendarId = calendarService.getCalendarIdForEmployee(employee.getEmployeeId(),
				meeting.getStartDateTime().toLocalDate());

		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setSlotStatus(SlotStatus.BOOKED);

		if (!Optional.ofNullable(existingCalendarId).isPresent()) {
			Calendar calendar = new Calendar();
			calendar.setEmployeeId(employee.getEmployeeId());
			calendar.setDate(meeting.getStartDateTime().toLocalDate());
			calendar = calendarService.saveCalendar(calendar);
			timeSlot.setCalendarId(calendar.getCalendarId());
		} else {
			timeSlot.setCalendarId(existingCalendarId);
		}

		timeSlotService.saveTimeSlot(timeSlot);
	}

	/**
	 * save the meeting using meeting repository
	 * 
	 * @param meetingDto
	 * @return
	 */
	private Meeting saveMeeting(final MeetingDto meetingDto) {
		Meeting meeting = new Meeting();
		BeanUtils.copyProperties(meetingDto, meeting);
		meeting.setOrganizerId(
				employeeService.getEmployeeByEmailId(meetingDto.getOrganizer().getEmailId()).getEmployeeId());
		return meetingRepository.save(meeting);
	}

	/**
	 * @param meetingDto
	 * @return
	 */
	public List<EmployeeDto> getConflictedParticipants(final MeetingDto meetingDto) {

		LocalDate meetingDate = meetingDto.getStartDateTime().toLocalDate();
		LocalTime meetingStartTime = meetingDto.getStartDateTime().toLocalTime();
		LocalTime meetingEndTime = meetingDto.getEndDateTime().toLocalTime();

		ArrayList<EmployeeDto> atendees = meetingDto.getAtendees();
		ArrayList<EmployeeDto> conflictedAtendees = new ArrayList<>();

		// for each attendee check if any conflict exists
		atendees.forEach(atendee -> {
			final boolean conflictExist = calendarService.checkAnyConflictExist(atendee, meetingDate, meetingStartTime,
					meetingEndTime);
			if (conflictExist) {
				log.info("Conflict exists for atendee: {}", atendee.getEmailId());
				conflictedAtendees.add(atendee);
			}
		});

		return conflictedAtendees;
	}

}
