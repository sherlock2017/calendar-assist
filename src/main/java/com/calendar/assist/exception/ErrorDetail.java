package com.calendar.assist.exception;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Object to carry error details
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Setter
@Getter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "error")
public class ErrorDetail {

	private String code;
	private String message;

	public ErrorDetail(String message) {
		super();
		this.message = message;
	}

}
