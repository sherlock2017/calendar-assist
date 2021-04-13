package com.calendar.assist.dto;

import java.time.LocalTime;

import com.calendar.assists.enums.SlotStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSlotDto {

	private LocalTime startTime;
	private LocalTime endTime;
	private SlotStatus slotStatus;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((slotStatus == null) ? 0 : slotStatus.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		TimeSlotDto other = (TimeSlotDto) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (slotStatus != other.slotStatus)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

}
