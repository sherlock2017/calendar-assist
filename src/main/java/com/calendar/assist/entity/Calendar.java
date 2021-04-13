package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.calendar.assist.id.CalendarId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CALENDAR")
@IdClass(value = CalendarId.class)
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger calendarId;
	
	private BigInteger employeeId;
	
	@Id
	private LocalDate date;

}
