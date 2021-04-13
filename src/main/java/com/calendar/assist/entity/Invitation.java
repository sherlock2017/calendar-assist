package com.calendar.assist.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "INVITATION")
@Getter
@Setter
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger invitationId;
	private BigInteger meetingId;
	private BigInteger employeeId;
	@Enumerated(EnumType.STRING)
	private InviteStatus inviteStatus;
}
