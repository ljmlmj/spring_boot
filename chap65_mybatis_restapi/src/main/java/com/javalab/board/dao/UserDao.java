package com.javalab.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.javalab.board.vo.User;



/*
 * 매퍼 인터페이스 : Service Layer와 매퍼xml(sql쿼리문)을 연결해주는 역할(bridge)
 */
@Mapper
public interface UserDao {
	
	List<User> getUserList();
	User getUser(int id);
	Integer register(User userUser);
	Integer register2(User userUser);
	Integer modify(User user);
	Integer remove(Integer id);
	User login(User user);
}