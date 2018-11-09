package net.vnict.shop.controller.api;

import net.vnict.shop.domain.entities.Category;
import net.vnict.shop.domain.entities.Product;
import net.vnict.shop.service.CategoryService;
import net.vnict.shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiCategoryController {

	private CategoryService categoryService;
	private ProductService productService;

    public ApiCategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/categories")
	public ResponseEntity<?> findAll() {
		Iterable<Category> categories = categoryService.findAll();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<List<Product>> findById(@PathVariable("id") Integer categoryId) {
		if (categoryId == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			List<Product> products = productService.findByCategoryId(categoryId);
			return new ResponseEntity<>(products, HttpStatus.OK);
		}
	}
}
