package net.vnict.shop.service;

import net.vnict.shop.domain.entities.User;

import java.util.List;

public interface UserService {

	List<User> findAll();
	
	User findOne(Integer id);
	
	void delete(Integer id);
	
	net.vnict.shop.domain.model.User checkLogin(net.vnict.shop.domain.model.User user);
	
	boolean register(User user);
}
