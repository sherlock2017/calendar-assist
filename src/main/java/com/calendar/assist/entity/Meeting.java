package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MEETING")
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger meetingId;
	private String title;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Integer duration;
	private String description;
	private BigInteger organizerId;

}
