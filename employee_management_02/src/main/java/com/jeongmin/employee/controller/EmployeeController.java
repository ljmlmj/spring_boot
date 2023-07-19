package com.jeongmin.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeongmin.employee.service.EmployeeService;
import com.jeongmin.employee.vo.EmployeeCommonDto;
import com.jeongmin.employee.vo.Employees;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/emp")
@Slf4j
public class EmployeeController {

	 @Autowired
	 private EmployeeService employeeService;
	
	 // 사원 목록 조회
	 @GetMapping("/list")
	 public String getEmployeeList(EmployeeCommonDto dto, Model model){
		 List<EmployeeCommonDto> empList = employeeService.getEmployeesList(dto);
		 model.addAttribute("list", empList);
		 return "/list"; 
	 }

	 // 사원 정보 보기
	 @GetMapping("/read")
	 public String getEmployees(@RequestParam("id") int id, Model model) {	
		 EmployeeCommonDto dto = employeeService.getEmployees(id);
		 model.addAttribute("employee", dto);		 
		 return "/view";
	 }
	 
	// 사원 등록 처리
	@PostMapping("/register")
	public String register(Employees emp){
		
		// 게시물 등록(저장)
		int result = employeeService.register(emp);
		
		// 저장후 목록 출력 컨트롤러 호출, redirect하면 사용자 화면의 주소창이 변경됨.
		return "redirect:/emp/list"; 
	}
	
	// 사원 수정폼
	@GetMapping("/modify")
	public String modify(@RequestParam("id") int id, Model model) {
		log.info("modify get 메소드");
		EmployeeCommonDto dto = employeeService.getEmployees(id);
		log.info("modify toString()" + dto.toString());
		model.addAttribute("employee", dto);
		return "/modify";
		
	}
}