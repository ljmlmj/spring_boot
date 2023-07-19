package com.javalab.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javalab.board.service.EmployeeService;
import com.javalab.board.vo.Criteria;
import com.javalab.board.vo.Department;
import com.javalab.board.vo.EmployeeCommonDto;
import com.javalab.board.vo.Employees;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/emp")
@Slf4j
public class RestEmployeeController {

	private final EmployeeService employeeService;
	
	public RestEmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/list")
	public List<EmployeeCommonDto> getEmployeeList(EmployeeCommonDto dto, @ModelAttribute("cri") Criteria cri){
		List<EmployeeCommonDto> employeeList = employeeService.getEmployeesList(dto);
		for (EmployeeCommonDto empDto : employeeList) {
			log.info(empDto.toString());
		}
		return employeeList;
	}
	

    @GetMapping("/read/{employeeId}")
    public EmployeeCommonDto getEmployees(@PathVariable("employeeId") int employeeId) {
       EmployeeCommonDto empDto = employeeService.getEmployees(employeeId); 
        return empDto;
    }

    @GetMapping("/register")
    public List<Department> registerForm(Employees emp) {
        return employeeService.getDepartmentList();
    }

   @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody Employees emp) {
       
       log.info("@RequestBody로 바인딩된 값 : " +  emp.toString());
        
       int result = employeeService.register(emp);
        if (result > 0) {
            return ResponseEntity.ok("insert ok");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("failure insert");
        }
    }
    
   
    @PutMapping("/edit/{employeeId}")
    public ResponseEntity<String> editEmployee(@PathVariable("employeeId") int employeeId, 
          @RequestBody Employees updatedEmp) {
        log.info("Editing employee with ID: " + employeeId);
        /*
        int result = employeeService.updateEmployee(employeeId, updatedEmp);
        if (result > 0) {
            return ResponseEntity.ok("edit ok");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("failure edit");
        }
        */
        return null;
    }   
    

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") int employeeId) {
        /*
        boolean deleted = employeeService.deleteEmployee(employeeId);
        if (deleted) {
            return ResponseEntity.ok("delete ok");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("failure delete");
        }
        */
        return null;
    }
	
}