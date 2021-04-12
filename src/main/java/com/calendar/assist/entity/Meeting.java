package com.calendar.assist.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meeting {

	private BigInteger meetingId;
	private String title;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Integer duration;
	private List<Employee> atendees;
	private String description;

}
