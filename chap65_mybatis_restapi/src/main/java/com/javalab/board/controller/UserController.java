package com.javalab.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javalab.board.service.UserService;
import com.javalab.board.vo.User;

import lombok.extern.slf4j.Slf4j;

/*
 * @RestController : 모든 메소드의 응답 결과를 JSON 타입 문자열로 반환
 */

@RestController
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	
	// 사용자 목록 조회
	@GetMapping("/list")
	public List<User> getUserList() {
		List<User> userList = userService.getUserList();
		return userList;
	}
	
	// 사용자 한명 조회
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		User user = userService.getUser(id);
		return user;
	}
	
	/*
	 * 사용자 등록 : 쿼리스트링으로 요청 
	 * 요청 형태 :: http://localhost:8080/register?name=오징어 &age=20&phone=010-2244-5533&address=울산광역시 
	 */
	/*
	@PostMapping("/register")
	public void register(@RequestParam("name") String name,
						@RequestParam("age") Integer age,
						@RequestParam("phone") String phone,
						@RequestParam("address") String address) {
		User user = new User(null, name, age, phone, address, null);
		userService.register(user);
	}
	*/
	/*
	 * @RequestBody는 Spring Framework에서 제공하는 어노테이션으로, 
	 * HTTP 요청의 본문(body)에 포함된 데이터를 자바 객체로 변환하는 역할.
	 * 
	 * post 요청, ajax, REST API와 같은 형태의 요청을 하면서 JSON 타입 문자열이
	 * 전달되어야 한다.
	 *
	 * 요청형태 : JSON Type 문자열
	 *  - 요청 url : http://localhost:8080/register
	 *  - jason json : {"name": "동태", "age": 25, "phone": "010-2244-5533", "address": "평택시"}
	 */
	@PostMapping("/register")
	public void register(@RequestBody User user) {
		userService.register2(user);
	}
	
	/*
	 * 사용자 수정 - @PutMapping
	 * Http request 
	 *  - put 요청 url : http://localhost:8080/modify
	 *  - body json string
	 *	{
	 *	    "id": 107,
	 *	    "name": "가자미",
	 *	    "age": 20,
	 *	    "phone": "010-2244-5533",
	 *	    "address": "평택시"
	 *	}
	 */
	@PutMapping("/modify")
	public void modify(@RequestBody User user) {
		userService.modify(user);
	}
	
	/*
	 * 삭제 - @DeleteMapping
	 * Http 요청 : 경로주소(도메인에 파라미터 전달)
	 *  - http://localhost:8080/remove/108 
	 */
	@DeleteMapping("/remove/{id}")
	public void remove(@PathVariable("id") Integer id) {
		userService.remove(id);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		log.info("login메소드 user.toString() : " + user.toString());
		User loginUser = userService.login(user);
		
		if (loginUser != null) {
			return new ResponseEntity<>(loginUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("일치하는 사용자가 없습니다", HttpStatus.UNAUTHORIZED);
		}
	}
	
	
	
	
	
}
