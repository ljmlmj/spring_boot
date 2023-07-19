package com.jeongmin.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeongmin.product.dto.CategoryDTO;
import com.jeongmin.product.dto.PageRequestDTO;
import com.jeongmin.product.dto.PageResultDTO;
import com.jeongmin.product.dto.ProductDTO;
import com.jeongmin.product.entity.Category;
import com.jeongmin.product.entity.Product;
import com.jeongmin.product.service.CategoryService;
import com.jeongmin.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

	// 카테고리 서비스 의존성 주입[생성자 주입]
	//@Autowired
    //private final ProductService productService;
    //private final CategoryService categoryService;
	
	// 카테고리 서비스 의존성 주입[생성자 주입]
    private final ProductService productService;
    private final CategoryService categoryService;
    
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // 카테고리 목록 조회
    @GetMapping("/list")
    public void getList(PageRequestDTO pageRequestDTO,
						Model model) {
    	PageResultDTO<ProductDTO, Product> result = productService.getList(pageRequestDTO);
        model.addAttribute("result", result);
    }

    @GetMapping("/read")
    public void getProductById(@RequestParam Integer productId, Model model) {
    	log.info("getProductById");
    	ProductDTO dto = productService.read(productId);
        model.addAttribute("product", dto);
    }


    @GetMapping("/register")
    public void registerForm(Model model, 
    						@ModelAttribute("productDTO") ProductDTO productDTO, 
    						BindingResult bindingResult) {
        log.info("createForm");
        
        List<CategoryDTO> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product", new Product());
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute @Valid ProductDTO product, 
    						BindingResult bindingResult, 
    						//RedirectAttributes ra,
    						Model model) {
        log.info("register");
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                log.error( error.getField() + " "+ error.getDefaultMessage());
            }
            // 카테고리 목록을 드롭다운리스트로 출력하기 위한 데이터
            List<CategoryDTO> categoryList = categoryService.getList();
            model.addAttribute("categoryList", categoryList);   
            model.addAttribute("product", product);
            log.info("register");
            return "product/register";
        }
        productService.register(product);
        return "redirect:/product/list";
    }

    @GetMapping("/modify")
    public void modify(@RequestParam("productId") Integer productId, 
    		@ModelAttribute("productDTO") ProductDTO productDTO,
    		BindingResult bindingResult,
    		Model model) {
    	
    	log.info("modify - get");
    	
    	ProductDTO dto = productService.read(productId);
            	
        List<CategoryDTO> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);
    	
        model.addAttribute("product", dto);
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute @Valid ProductDTO dto,
						BindingResult bindingResult,
						Model model) {
    	
    	log.info("modify - post dto : " + dto.toString());
    	
        if (bindingResult.hasErrors()) {        	
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                log.error( error.getField() + " "+ error.getDefaultMessage());
            }
            List<CategoryDTO> categoryList = categoryService.getList();
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("product", dto);
            return "product/modify";
        }
        productService.modify(dto);
        return "redirect:/product/list";
    }

    // 카테고리 삭제
    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Integer productId) {
       
        boolean deleted = productService.remove(productId);
        
        return "redirect:/product/list";
    }
}