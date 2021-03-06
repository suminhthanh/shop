package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Product;

import java.util.List;

public interface ProductService {

	Iterable<Product> findAll();

	List<Product> findByCategoryId(Integer categoryId);

	Product findOne(Integer id);

	long countAll();

	Product save(Product product);

	void delete(Integer id);

}
