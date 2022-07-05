package com.ecommerce.categoryService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.categoryService.entity.Category;
import com.ecommerce.categoryService.repository.CategoryRepository;

@SpringBootTest(classes = { CategoryServiceTest.class })
public class CategoryServiceTest {
	@Mock
	CategoryRepository categoryRepo;

	@InjectMocks
	CategoryService categoryService;
	
	List<Category> categories;

	@Test
	@Order(2)
	public void test_listCategories() {

		List<Category> categories = new ArrayList<Category>();

		categories.add(new Category(1, "Mobiles"));

		categories.add(new Category(2, "Watches"));

		categories.add(new Category(3, "Laptops"));

		categories.add(new Category(4, "Tv"));

		when(categoryRepo.findAll()).thenReturn(categories);

		assertEquals(4, categoryService.listCategories().size());

	}

	@Test
	@Order(1)
	public void test_getSingleCategory() {

		List<Category> categories = new ArrayList<Category>();

		categories.add(new Category(1, "Mobiles"));

		categories.add(new Category(2, "Watches"));

		categories.add(new Category(3, "Laptops"));

		categories.add(new Category(4, "Tv"));

		int categoryId = 1;

		when(categoryRepo.findAll()).thenReturn(categories);

		assertEquals(categoryId, categoryService.findCategoryById(categoryId).getCategoryId());

	}
	
	@Test
	@Order(3)
	public void test_createCategory() {
		Category category = new Category(5, "Routers");
		
		when(categoryRepo.insert(category)).thenReturn(category);
		
		assertEquals(category,categoryService.createCategory(category));
	}
	
	@Test
	@Order(4)
	public void test_editCategory() {
		Category category = new Category(6, "Gadjets");
		
		when(categoryRepo.save(category)).thenReturn(category);
		
		assertEquals(category,categoryService.editCategory(category));
	}
	
	@Test
	@Order(5)
	public void test_deleteCategory() {
		Category category = new Category(6, "Gadjets");
		
		categoryService.deleteCategory(category);
		verify(categoryRepo,times(1)).delete(category);
		
		
	}

	
}
