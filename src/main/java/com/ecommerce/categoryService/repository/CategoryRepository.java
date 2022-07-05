package com.ecommerce.categoryService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.categoryService.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Integer> {
	Category findByCategoryId(Integer categoryId);
}
