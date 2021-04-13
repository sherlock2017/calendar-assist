package com.calendar.assist.id;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CalendarId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger calendarId;

	private LocalDate date;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendarId == null) ? 0 : calendarId.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalendarId other = (CalendarId) obj;
		if (calendarId == null) {
			if (other.calendarId != null)
				return false;
		} else if (!calendarId.equals(other.calendarId))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

}
