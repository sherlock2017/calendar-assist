package com.calendar.assist.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendar.assist.entity.TimeSlot;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, BigInteger>{

	@Query("select ts from TimeSlot ts where ts.calendarId = :calendarId")
	public List<TimeSlot> getBookedTimeSlots(@Param("calendarId") BigInteger calendarId);

}
