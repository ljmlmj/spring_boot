package com.javalab.board.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Employees {

	 private int employeeId;
	 private String lastName;
	 private String firstName;
	 private String email;
	 private String phoneNumber;
	 private Date hireDate;
	 private String jobId;
	 private long salary;
	 private int commissionPct;
	 private int managerId;
	 private int departmentId;
	 
}