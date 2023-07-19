package com.jeongmin.employee.service;

import java.util.List;

import com.jeongmin.employee.vo.EmployeeCommonDto;
import com.jeongmin.employee.vo.Employees;


public interface EmployeeService {

	List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	EmployeeCommonDto getEmployees(int employeeId);
	int register(Employees emp);
	int modify(int employeeId);
	EmployeeCommonDto getEmployeeById(EmployeeCommonDto emp);

}