package com.javalab.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javalab.product.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
