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

	@GetMapping("/product/latest")
	public ResponseEntity<List<Product>> findLatest() {
		return new ResponseEntity<>(productService.findLatest(0, 10), HttpStatus.OK);
	}
	
	@GetMapping("/product/search")
	public ResponseEntity<List<Product>> search(@RequestParam("keyword") String keyword) {
		return new ResponseEntity<>(productService.search(keyword), HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(productService.findByCategoryId(id), HttpStatus.OK);
	}
		
}
