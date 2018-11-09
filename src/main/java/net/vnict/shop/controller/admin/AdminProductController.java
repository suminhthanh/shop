package net.vnict.shop.controller.admin;

import net.vnict.shop.domain.entities.Product;
import net.vnict.shop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class AdminProductController {

    private ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public ResponseEntity<?> findAll() {
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @PostMapping("/admin/product/")
    public ResponseEntity<?> save(@Valid @RequestParam final Product product) {
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/admin/product/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Product product = productService.findOne(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
