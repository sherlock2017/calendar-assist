package com.calendar.assist.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calendar.assist.entity.Employee;

/**
 * Repository for Employee
 * 
 * @author Rishabh Jain
 * @since 4/13/2021
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, BigInteger> {

//	@Query("Select emp from Employee emp where emp.emailId = :emailId")
//	public Employee getEmployeeByEmailId(@Param("emailId") final String emailId);
//	
	Employee findByEmailId(String emailId);
}
