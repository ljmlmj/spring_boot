package com.jeongmin.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jeongmin.employee.vo.Criteria;
import com.jeongmin.employee.vo.Department;
import com.jeongmin.employee.vo.EmployeeCommonDto;
import com.jeongmin.employee.vo.Employees;
import com.jeongmin.employee.vo.Job;



/*
 * 매퍼 인터페이스 : Service Layer와 매퍼xml(sql쿼리문)을 연결해주는 역할(bridge)
 */
@Mapper
public interface EmployeeDao {
	
	List<EmployeeCommonDto> getEmployeesList(EmployeeCommonDto dto);
	EmployeeCommonDto getEmployees(int employeeId);
	public int getTotalEmployees(Criteria cri);	// 페이징을 위한 사원숫자 조회	
	public int register(Employees emp);
	public List<Department> getDepartmentList();
	public List<Job> getJobList();
	public int modify(int employeeId);
	EmployeeCommonDto getEmployeeById(EmployeeCommonDto emp);

}