package com.calendar.assist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.entity.Employee;
import com.calendar.assist.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee getEmployeeById(final String emailId) {
		return employeeRepository.getEmployeeByEmailId(emailId);
	}

}
