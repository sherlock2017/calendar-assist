package com.calendar.assist.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.calendar.assist.entity.Calendar;
import com.calendar.assist.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	static List<Employee> employeeList = new ArrayList<Employee>();

	static {
		employeeList.add(new Employee("Rishabh Jain", "rishabh.jain@gmail.com", new Calendar()));
		employeeList.add(new Employee("Priya Jain", "priya.jain@gmail.com", new Calendar()));
		employeeList.add(new Employee("Megha Jain", "megha.jain@gmail.com", new Calendar()));
		employeeList.add(new Employee("Prabal Jain", "prabal.jain@gmail.com", new Calendar()));
	}

	public Optional<Employee> getEmployeeById(String emailId) {
		log.info("email: {}", emailId);
		return employeeList.stream().filter(employee -> emailId.equalsIgnoreCase(employee.getEmailId())).findFirst();
	}

}
