package com.calendar.assist.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MeetingDto {

	private String title;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Integer duration;
	private ArrayList<EmployeeDto> atendees;
	private String description;

}
