package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.calendar.assist.id.MeetingId;

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
@IdClass(value = MeetingId.class)
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger meetingId;
	private String title;
	@Id
	private LocalDateTime startDateTime;
	@Id
	private LocalDateTime endDateTime;
	private Integer duration;
	private String description;
	private BigInteger organizerId;

}
