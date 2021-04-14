package com.calendar.assist.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendar.assist.entity.TimeSlot;

/**
 * Repository for TimeSlot
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, BigInteger> {

	List<TimeSlot> findByCalendarId(BigInteger calendarId);

}
