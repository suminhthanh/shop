package net.vnict.shop.controller.admin;

import net.vnict.shop.domain.entities.Order;
import net.vnict.shop.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminOrderController {

    private OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public ResponseEntity<?> findAll() {
        Iterable<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/admin/order/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Order order = orderService.findOne(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/admin/order/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
