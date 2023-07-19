package com.jeongmin.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jeongmin.product.entity.Category;
import com.jeongmin.product.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("select p, c from Product p left join p.category c where p.productId = :productId")
	Object getProductWithCategory(@Param("productId") Integer productId);
	
}