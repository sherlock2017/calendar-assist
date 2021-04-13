package com.calendar.assist.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This entity carry employee details
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYEE_ID")
	private BigInteger employeeId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
}
