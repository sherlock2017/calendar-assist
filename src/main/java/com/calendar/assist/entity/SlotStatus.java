package com.calendar.assist.entity;

public enum SlotStatus {
	AVAILABLE("Available"), BOOKED("Booked");
	
	String s;
	
	SlotStatus(String s) {
		this.s = s;
	}
}
