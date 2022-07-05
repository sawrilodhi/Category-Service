package com.ecommerce.categoryService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.categoryService.entity.Category;
import com.ecommerce.categoryService.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;

	public Category createCategory(Category category) {

		return categoryRepo.insert(category);
	}

	public List<Category> listCategories() {
		List<Category> categories= categoryRepo.findAll();
		return categories;

	}

	public Category findCategoryById(Integer categoryId) {

		List<Category> categories = categoryRepo.findAll();

		Category category = null;

		for (Category cat : categories) {

			if (cat.getCategoryId() == categoryId)

				category = cat;

		}

		return category;
	}

	public void deleteCategory(Category category) {

		categoryRepo.delete(category);

	}

	public Category editCategory(Category category) {

		return categoryRepo.save(category);

	}
}
