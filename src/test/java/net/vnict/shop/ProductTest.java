package net.vnict.shop;

import net.vnict.shop.domain.entities.Category;
import net.vnict.shop.domain.entities.Product;
import net.vnict.shop.service.CategoryService;
import net.vnict.shop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Test
    public void testProduct_getOneOK() {
        Product product = productService.findOne(1);
        assertThat("Pizza thập cẩm cao cấp").isEqualTo(product.getName());
    }

    @Test
    public void testProduct_getOneNotFound() {
        //  assertThrows(NoSuchElementException.class, () -> {
        productService.findOne(1123123);
        //  });
    }

    @Test
    public void testProduct_getAll() {
        List<Product> products = (List<Product>) productService.findAll();
        assertThat(0).isLessThan(products.size());
    }


    @Test
    public void testProduct_saveOK() {
        Category category = categoryService.findOne(2);
        Product product = new Product();
        product.setCategory(category);
        product.setName("Pizza Viet Nam");
        product.setDescription("Mo ta");
        product.setPrice(50000);
        productService.save(product);
        List<Product> products = (List<Product>) productService.findAll();
        Product product1 = productService.findOne(products.size() - 1);
        assertThat(product.getName()).isEqualTo(product1.getName());
    }

    @Test
    public void testProduct_saveFail_categoryNotFound() throws Exception {
        Category category = categoryService.findOne(666);
        Product product = new Product();
        product.setCategory(category);
        product.setName("Pizza Viet Nam");
        product.setDescription("Mo ta");
        product.setPrice(50000);
        assertThrows(DataIntegrityViolationException.class, () -> {
            productService.save(product);
        });
    }

    @Test
    void testProduct_deleteOK() {
        Product product = productService.findOne((int) (productService.countAll() - 1));
        productService.delete((int) (productService.countAll() - 1));
        assertThat(product).isNotEqualTo(productService.findOne((int) (productService.countAll() - 1)));
    }

    @Test
    void testProduct_deleteFail_productNotFound() {
        assertThrows(NoSuchElementException.class, () -> {
            productService.delete(123);
        });

    }

    @Test
    void testProduct_countAll() {
        assertThat(0).isLessThan((int) productService.countAll());
    }
}