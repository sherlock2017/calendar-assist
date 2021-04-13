package com.calendar.assist.exception;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "error")
public class ErrorDetail {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	private String code;
	private String message;

	public ErrorDetail(String message) {
		super();
		this.message = message;
	}

}
