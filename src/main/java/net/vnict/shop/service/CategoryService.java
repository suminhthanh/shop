package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Category;

public interface CategoryService {

	Iterable<Category> findAll();

	Category findOne(Integer id);

	Category save(Category category);

	boolean delete(Integer id);
	
}
