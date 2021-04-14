package com.calendar.assist.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

import java.math.BigInteger;

import com.calendar.assist.entity.Employee;
import com.calendar.assist.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	public void getEmployeeEmailIdTest() {
		
		String emailId = "rishabh.jain@gmail.com";
		when(employeeRepository.findByEmailId(ArgumentMatchers.anyString()))
				.thenReturn(new Employee(BigInteger.ONE, "Rishabh Jain", emailId));

		Employee employee = employeeService.getEmployeeByEmailId(emailId);
		System.out.println(employee);
		assertNotNull(employee);
	}
}
