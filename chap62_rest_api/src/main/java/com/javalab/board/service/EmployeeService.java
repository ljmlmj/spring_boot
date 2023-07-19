package com.javalab.board.service;

import java.util.List;

import com.javalab.board.vo.Criteria;
import com.javalab.board.vo.Department;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;
import com.javalab.board.vo.Job;


public interface EmployeeService {

	List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	EmployeeCommonDto getEmployees(int employeeId);
	int register(Employees emp);
	int getTotalEmployees(Criteria cri);
	List<Department> getDepartmentList();
	List<Job> getJobList();

}