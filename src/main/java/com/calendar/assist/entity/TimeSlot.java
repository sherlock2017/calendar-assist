package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "TIME_SLOT")
public class TimeSlot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger slotId;
	private BigInteger calendarId;
	private LocalTime startTime;
	private LocalTime endTime;
	@Enumerated(EnumType.STRING)
	private SlotStatus slotStatus;
	
}
