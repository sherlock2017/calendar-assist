package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import com.calendar.assist.id.MeetingId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity to carry Meeting details
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Entity
@Table(name = "MEETING")
@IdClass(value = MeetingId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEETING_ID")
	private BigInteger meetingId;
	
	@Column(name = "TITLE")
	private String title;
	
	@Id
	@Column(name = "START_DATE_TIME")
	private LocalDateTime startDateTime;
	
	@Id
	@Column(name = "END_DATE_TIME")
	private LocalDateTime endDateTime;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "ORGANIZER_ID")
	private BigInteger organizerId;

}
