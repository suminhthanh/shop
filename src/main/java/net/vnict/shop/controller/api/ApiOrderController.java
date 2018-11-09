package net.vnict.shop.controller.api;

import net.vnict.shop.domain.model.Order;
import net.vnict.shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiOrderController {

	private OrderService orderService;

	public ApiOrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping("/order/")
	public ResponseEntity<Boolean> save(@RequestBody Order order) {
		orderService.save(order);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
}
