package com.innova.ecommerce.repository;

import com.innova.ecommerce.entity.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public Optional<Category> findByCategoryNameIgnoreCase(String categoryName);
    public Optional<Category> findByCategoryNameContainingIgnoreCase(String categoryName);

}
