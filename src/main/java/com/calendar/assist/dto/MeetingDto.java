package com.calendar.assist.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeetingDto {

	private String title;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private ArrayList<EmployeeDto> atendees;
	private String description;
	private EmployeeDto organizer;

}
