package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.Column;
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

/**
 * Entity to carry calendar details
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Entity
@Table(name = "CALENDAR")
@IdClass(value = CalendarId.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Calendar {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CALENDAR_ID")
	private BigInteger calendarId;
	
	@Column(name = "EMPLOYEE_ID")
	private BigInteger employeeId;
	
	@Id
	@Column(name = "DATE")
	private LocalDate date;

}
