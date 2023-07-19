package com.javalab.board.service;

import java.util.List;

import com.javalab.board.vo.Employees;


public interface EmployeeService {

	List<Employees> getEmployeesList();
	Employees getMember(int employeeId);

}