package net.vnict.shop.service;

import net.vnict.shop.domain.entities.Category;
import net.vnict.shop.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
    @Transactional(readOnly = true)
	public Category findOne(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(Integer id) {
		Category category = categoryRepository.findById(id).get();
		categoryRepository.delete(category);
	}
}
