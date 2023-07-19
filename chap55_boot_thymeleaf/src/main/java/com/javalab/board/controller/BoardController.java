package com.javalab.board.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.javalab.board.vo.ProductVo;
import com.javalab.board.vo.UserVo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {

		@GetMapping("/")
		public String home(Model model, HttpSession session) {
			log.info("여기는 DemoController home()");
			
			// 1. 단순한 값 출력
			//new Date() : java.util.Date 인식안되면 지웠다가 새로 타이핑
			model.addAttribute("date", new Date());	
			
			// 2. 숫자 판별
			int num1 = 10;
			int num2 = 20;
			model.addAttribute("num1", num1);
			model.addAttribute("num2", num2);
			
			// 3. if else
			model.addAttribute("name", "홍길동");
			
			// 4. 이미지 출력
			model.addAttribute("imgName", "soccer.png");
			
			// 5. 컬렉션 출력
			List<UserVo> userList = new ArrayList<>();
			userList.add(new UserVo("dream", "홍길동", 18));
			userList.add(new UserVo("tango", "우수한", 25));
			userList.add(new UserVo("fineapple", "여미지", 30));
			model.addAttribute("userList", userList);
			
			// 6. 세션
			// 파라미터로 세션 추가 후 테스트 진행 HttpSession session
			UserVo user = new UserVo("abc1234", "John Oh", 25);
			session.setAttribute("user", user);
			
			// 7. 숫자 numbers
			ProductVo product = new ProductVo();
			product.setProductId("P123");
			product.setName("인공지능 냉장고");
			product.setPrice(1234567L);
			product.setReceiptDate(LocalDateTime.now());
			model.addAttribute("product", product);
			
			double number = 12345.6789;
			
			
			
			
			return "index";
		}	
	}