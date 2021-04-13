package com.calendar.assist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calendar.assist.entity.Employee;
import com.calendar.assist.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * @param emailId
	 * @return
	 */
	public Employee getEmployeeByEmailId(final String emailId) {
		return employeeRepository.getEmployeeByEmailId(emailId);
	}

}
