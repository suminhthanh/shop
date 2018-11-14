package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Product;
import net.vnict.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findOne(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public long countAll() {
        return productRepository.count();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        productRepository.delete(product);
    }
}
