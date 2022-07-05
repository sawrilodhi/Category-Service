package com.ecommerce.categoryService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.categoryService.entity.Category;
import com.ecommerce.categoryService.service.CategoryService;

@SpringBootTest(classes = { CategoryControllerTest.class })
public class CategoryControllerTest {

	@Mock
	CategoryService categoryService;

	@InjectMocks
	CategoryController categoryController;

	Category category;

	@Test
	@Order(2)
	public void test_listAllCategories() {

		List<Category> categories = new ArrayList<Category>();

		categories.add(new Category(1, "Mobiles"));

		categories.add(new Category(2, "Watches"));

		categories.add(new Category(3, "Laptops"));

		categories.add(new Category(4, "Tv"));

		when(categoryService.listCategories()).thenReturn(categories);

		ResponseEntity<List<Category>> res = categoryController.listAllCategories();

		assertEquals(HttpStatus.FOUND, res.getStatusCode());

		assertEquals(4, res.getBody().size());
	}

	@Test

	@Order(3)

	public void test_updateCategory() {

		category = new Category(5, "Gadgets");

		Integer categoryId = 5;

		when(categoryService.findCategoryById(categoryId)).thenReturn(category);

		when(categoryService.editCategory(category)).thenReturn(category);

		ResponseEntity<Category> res = categoryController.updateCategory(categoryId, category);

		assertEquals(HttpStatus.OK, res.getStatusCode());

		assertEquals(5, res.getBody().getCategoryId());

		assertEquals("Gadgets", res.getBody().getCategoryName());

	}

	@Test
	@Order(1)
	public void test_saveCategory() {

		category = new Category(5, "Gadjets");

		when(categoryService.createCategory(category)).thenReturn(category);

		ResponseEntity<Category> res = categoryController.saveCategory(category);

		assertEquals(HttpStatus.CREATED, res.getStatusCode());

		assertEquals(category, res.getBody());

	}

	@Test
	@Order(5)
	public void test_getCategoryById() {
		category = new Category(6, "Headset");
		Integer categoryId = 6;

		when(categoryService.findCategoryById(categoryId)).thenReturn(category);
		ResponseEntity<Category> res = categoryController.getCategoryById(categoryId);

		assertEquals(HttpStatus.FOUND, res.getStatusCode());

		assertEquals(categoryId, res.getBody().getCategoryId());

	}

	@Test
	@Order(4)
	public void test_deleteCategory() {
		category = new Category(7, "Accessories");
		Integer categoryId = 7;

		when(categoryService.findCategoryById(categoryId)).thenReturn(category);
		ResponseEntity<Category> res = categoryController.deleteCategory(categoryId);

		assertEquals(HttpStatus.OK, res.getStatusCode());

	}

}
