package net.vnict.shop.service;

import net.vnict.shop.domain.model.Order;
import net.vnict.shop.domain.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
	
	Iterable<net.vnict.shop.domain.entities.Order> findAll();
	
	Page<net.vnict.shop.domain.entities.Order> findLatest(int page, int size);
	
	List<net.vnict.shop.domain.entities.Order> findByUser(User user);
	
	net.vnict.shop.domain.entities.Order findOne(Integer id);
	
	long countAll();
	
	void save(Order order);
	
	void delete(Integer orderId);
	
}
