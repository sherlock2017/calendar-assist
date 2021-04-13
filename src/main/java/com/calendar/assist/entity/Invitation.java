package com.calendar.assist.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.calendar.assists.enums.InviteStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity to carry invitation details
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Entity
@Table(name = "INVITATION")
@Getter
@Setter
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INVITATION_ID")
	private BigInteger invitationId;
	
	@Column(name = "MEETING_ID")
	private BigInteger meetingId;
	
	@Column(name = "EMPLOYEE_ID")
	private BigInteger employeeId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "INVITE_STATUS")
	private InviteStatus inviteStatus;
}
