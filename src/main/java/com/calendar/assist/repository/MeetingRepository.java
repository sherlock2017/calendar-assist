package com.calendar.assist.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.id.MeetingId;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, MeetingId> {

	@Query("select m from Meeting m where m.organizerId = :organizerId and m.startDateTime = :meetingStartDateTime and m.endDateTime = :meetingEndDateTime")
	public Meeting getMeeting(@Param("organizerId") final BigInteger organizerId,
			@Param("meetingStartDateTime") final LocalDateTime meetingStartDateTime,
			@Param("meetingEndDateTime") final LocalDateTime meetingEndDateTime);
}
