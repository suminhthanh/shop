package net.vnict.shop.controller.api;

import net.vnict.shop.domain.entities.Product;
import net.vnict.shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiProductController {

	private ProductService productService;

	public ApiProductController(ProductService productService) {
		this.productService = productService;
	}
		
}
