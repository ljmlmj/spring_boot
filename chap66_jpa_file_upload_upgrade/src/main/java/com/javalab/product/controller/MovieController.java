package com.javalab.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javalab.product.dto.MovieDTO;
import com.javalab.product.dto.PageRequestDTO;
import com.javalab.product.dto.PageResultDTO;
import com.javalab.product.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

	//@RequiredArgsConstructor + final 의존성주입
    private final MovieService movieService; 

    
    @GetMapping("/register")
    public void register(){
    	// 처리 페이지는 /movie/register.html // 경로명이 반환 페이지가 됨
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
        log.info("movieDTO: " + movieDTO);
        Long mno = movieService.register(movieDTO);
        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("pageRequestDTO: " + pageRequestDTO);
        
        PageResultDTO<MovieDTO, Object[]> result = movieService.getList(pageRequestDTO);
        model.addAttribute("result", result);
        
        log.info("list pageRequestDTO result " + result);
    }

    /*
     * 1. movie/read 요청 : movie/read.html 페이지로 이동
     * 2. movie/modify 요청 : movie/modify.html 페이지로 이동
     */
    @GetMapping({"/read", "/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model ){
        log.info("mno: " + mno);
        MovieDTO movieDTO = movieService.getMovie(mno);
        model.addAttribute("dto", movieDTO);
    }


}
