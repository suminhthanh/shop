package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Category;

public interface CategoryService {

	Iterable<Category> findAll();

	Category findOne(Integer id);

	long countAll();

	Category save(Category category);

	void delete(Integer id);
	
}
