package com.calendar.assist.repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendar.assist.dto.EmployeeDto;
import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Meeting;
import com.calendar.assist.id.CalendarId;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, CalendarId> {

	@Query("select cal.calendarId from Calendar cal where cal.employeeId = :employeeId and cal.date = :calendarDate")
	public BigInteger getCalendarIdForEmployee(@Param("employeeId") final BigInteger employeeId,
			@Param("calendarDate") final LocalDate calendarDate);

}
