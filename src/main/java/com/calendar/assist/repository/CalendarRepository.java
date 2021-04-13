package com.calendar.assist.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calendar.assist.entity.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, BigInteger> {

}
