package com.javalab.board.service;

import java.util.List;


import com.javalab.board.vo.User;


public interface UserService {

	List<User> getUserList();
	User getUser(Integer id);
	Integer register(User userUser);
	Integer register2(User user);
	Integer modify(User user);
	Integer remove(Integer id);
	User login(User user);

}