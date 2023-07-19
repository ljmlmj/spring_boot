package com.jeongmin.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeongmin.employee.dao.EmployeeDao;
import com.jeongmin.employee.vo.EmployeeCommonDto;
import com.jeongmin.employee.vo.Employees;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto) {
		return dao.getEmployeesList(dto);
	}

	@Override
	public EmployeeCommonDto getEmployees(int employeeId) {
		
		return dao.getEmployees(employeeId);
	}

	@Override
	public int register(Employees emp) {
		return dao.register(emp);
	}

	@Override
	public int modify(int employeeId) {
		// TODO Auto-generated method stub
		return dao.modify(employeeId);
	}

	@Override
	public EmployeeCommonDto getEmployeeById(EmployeeCommonDto emp) {
		return dao.getEmployeeById(emp);
	}

}