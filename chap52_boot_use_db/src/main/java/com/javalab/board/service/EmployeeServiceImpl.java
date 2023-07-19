package com.javalab.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.board.dao.EmployeeDao;
import com.javalab.board.vo.Employees;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public List<Employees> getEmployeesList() {

		return dao.getEmployeesList();
	}

	@Override
	public Employees getMember(int employeeId) {
		
		return dao.getMember(employeeId);
	}

}