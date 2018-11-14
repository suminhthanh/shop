package net.vnict.shop.service;

import net.vnict.shop.domain.model.Order;
import net.vnict.shop.domain.entities.User;

import java.util.List;

public interface OrderService {
	
	Iterable<net.vnict.shop.domain.entities.Order> findAll();
	
	List<net.vnict.shop.domain.entities.Order> findByUser(User user);
	
	net.vnict.shop.domain.entities.Order findOne(Integer id);
	
	void save(Order order);
	
	boolean delete(Integer orderId);
	
}
