package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.calendar.assists.enums.SlotStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity to carry TimeSlot details
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Entity
@Table(name = "TIME_SLOT")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SLOT_ID")
	private BigInteger slotId;
	
	@Column(name = "CALENDAR_ID")
	private BigInteger calendarId;
	
	@Column(name = "START_TIME")
	private LocalTime startTime;
	
	@Column(name = "END_TIME")
	private LocalTime endTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SLOT_STATUS")
	private SlotStatus slotStatus;
	
}
