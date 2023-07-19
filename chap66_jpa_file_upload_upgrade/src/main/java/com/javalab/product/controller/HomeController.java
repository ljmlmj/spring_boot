package com.javalab.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 업로드 화면을 띄워주는 역할의 컨트롤러
 * @author 505-t
 *
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String uploadEx() {
    	return "redirect:movie/list";

    }
}
