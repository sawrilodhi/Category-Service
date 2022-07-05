package com.ecommerce.categoryService.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.categoryService.entity.Category;
import com.ecommerce.categoryService.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/")
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		try {
			category = categoryService.createCategory(category);

			return new ResponseEntity<Category>(category, HttpStatus.CREATED);

		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<Category>> listAllCategories() {
		try {

			List<Category> categories = categoryService.listCategories();

			return new ResponseEntity<List<Category>>(categories, HttpStatus.FOUND);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Integer categoryId) {
		try {
			Category category = categoryService.findCategoryById(categoryId);
			return new ResponseEntity<Category>(category, HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Category> deleteCategory(@PathVariable Integer categoryId) {
		Category category = null;
		try {
			category = categoryService.findCategoryById(categoryId);
			categoryService.deleteCategory(category);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(category,HttpStatus.OK);

	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId, @RequestBody Category category) {

		try {
			Category existCategory = categoryService.findCategoryById(categoryId);

			existCategory.setCategoryName(category.getCategoryName());

			Category updateCategory = categoryService.editCategory(existCategory);

			return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}

	}
}
