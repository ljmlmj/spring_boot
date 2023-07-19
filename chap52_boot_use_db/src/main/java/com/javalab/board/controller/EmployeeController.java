package com.javalab.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javalab.board.service.EmployeeService;
import com.javalab.board.vo.Employees;


@Controller
@RequestMapping("/emp")
public class EmployeeController {

	 @Autowired
	 private EmployeeService employeeService;
	
	 @GetMapping("/list")
	 public String getEmployeeList(Model model){
		 List<Employees> list = employeeService.getEmployeesList();
		 model.addAttribute("list", list);
		 return "/list"; 
	 }

	 @GetMapping("/read")
	 public String getEmployee(@RequestParam("id") int id, Model model) {		 
		 model.addAttribute("employee", employeeService.getMember(id));		 
		 return "/view";
	 }
}