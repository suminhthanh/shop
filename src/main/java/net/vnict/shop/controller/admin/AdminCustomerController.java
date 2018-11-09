package net.vnict.shop.controller.admin;

import net.vnict.shop.domain.entities.Order;
import net.vnict.shop.domain.entities.User;
import net.vnict.shop.service.OrderService;
import net.vnict.shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminCustomerController {

	private UserService userService;

	private OrderService orderService;

    public AdminCustomerController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/admin/customer")
    public ResponseEntity<?> findAll() {
		Iterable<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/admin/customer/{id}/orders")
    public ResponseEntity<?> showOrdersByUser(@PathVariable("id") Integer id) {
		User customer = userService.findOne(id);
		List<Order> orders =  orderService.findByUser(customer);
		return new   ResponseEntity<>(orders, HttpStatus.OK);
	}

	@DeleteMapping("/admin/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
