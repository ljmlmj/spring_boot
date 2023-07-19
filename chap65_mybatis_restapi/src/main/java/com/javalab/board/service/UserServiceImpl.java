package com.javalab.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javalab.board.dao.UserDao;
import com.javalab.board.vo.User;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	public List<User> getUserList() {
		return dao.getUserList();
	}

	@Override
	public User getUser(Integer id) {
		return dao.getUser(id);
	}

	@Override
	public Integer register(User user) {
		return dao.register(user);
	}
	
	@Override
	public Integer register2(User user) {
		return dao.register2(user);
	}

	@Override
	public Integer modify(User user) {
		return dao.modify(user);
		
	}

	@Override
	public Integer remove(Integer id) {
		return dao.remove(id);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return dao.login(user);
	}


}