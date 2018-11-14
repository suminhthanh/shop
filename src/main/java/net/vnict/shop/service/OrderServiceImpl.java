package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Item;
import net.vnict.shop.domain.entities.User;
import net.vnict.shop.domain.model.Order;
import net.vnict.shop.repository.OrderRepository;
import net.vnict.shop.repository.ProductRepository;
import net.vnict.shop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	private UserRepository userRepository;

	private ProductRepository productRepository;

	public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<net.vnict.shop.domain.entities.Order> findAll() {
		return orderRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<net.vnict.shop.domain.entities.Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public net.vnict.shop.domain.entities.Order findOne(Integer id) {
		return orderRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(Order order) {
		// Current user
		User currentUser = userRepository.findByEmail(order.getUser().getEmail());
		
		// Set order
		net.vnict.shop.domain.entities.Order dbOrder = new net.vnict.shop.domain.entities.Order();
		dbOrder.setName(order.getName());
		dbOrder.setAddress(order.getAddress());
		dbOrder.setPhone(order.getPhone());
		dbOrder.setNote(order.getNote());
		dbOrder.setUser(currentUser);
		
		// Set items
		Item item;
		for (net.vnict.shop.domain.model.Item e : order.getItems()) {
			item = new Item();
			item.setOrder(dbOrder);
			item.setProduct(productRepository.findById(e.getProductId()).get());
			item.setQuantity(e.getQuantity());
			item.setPrice(e.getProductPrice());
			dbOrder.addItem(item);
		}
		
		orderRepository.save(dbOrder);
	}

	@Override
	public boolean delete(Integer orderId) {
		net.vnict.shop.domain.entities.Order order = orderRepository.findById(orderId).orElseThrow(()-> new NoSuchElementException());
		orderRepository.delete(order);
		return true;
	}
}
