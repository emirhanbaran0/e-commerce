package com.innova.ecommerce.service;

import com.innova.ecommerce.entity.dto.request.category.CategorySaveRequestDto;
import com.innova.ecommerce.entity.dto.request.category.CategoryUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.category.CategoryResponseDto;
import com.innova.ecommerce.exception.CategoryNotFoundException;

import java.util.List;


public interface CategoryService {

    public List<CategoryResponseDto> getCategories();

    public CategoryResponseDto getCategoryByName(String name) throws CategoryNotFoundException;

    public CategoryResponseDto getCategoryByNameContaining(String name) throws CategoryNotFoundException;

    public String saveCategory(CategorySaveRequestDto category);

    public String updateCategory(int categoryId, CategoryUpdateRequestDto categoryUpdateRequestDto)throws CategoryNotFoundException;

    public String deleteCategory(int id) throws CategoryNotFoundException;

}
