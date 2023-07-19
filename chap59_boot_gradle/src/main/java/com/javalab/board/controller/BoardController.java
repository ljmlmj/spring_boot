package com.javalab.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javalab.board.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		log.info("여기는  BoardController home()");
		
		// 컬렉션 생성
		UserVo user = new UserVo("dream", "홍길동", 18);
		model.addAttribute("user", user);
		
		return "index";
	}
}
