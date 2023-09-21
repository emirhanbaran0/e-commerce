package com.innova.ecommerce.service.impl;

import com.innova.ecommerce.entity.dto.request.category.CategorySaveRequestDto;
import com.innova.ecommerce.entity.dto.request.category.CategoryUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.category.CategoryResponseDto;
import com.innova.ecommerce.entity.model.Category;
import com.innova.ecommerce.exception.CategoryNotFoundException;
import com.innova.ecommerce.repository.CategoryRepository;
import com.innova.ecommerce.service.CategoryService;
import com.innova.ecommerce.util.GeneralConverter;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableCaching
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final GeneralConverter generalConverter;
    private final Logger log = Logger.getLogger(CategoryServiceImpl.class.getName());
    @Override
    public List<CategoryResponseDto> getCategories() {
        List<Category> categories=categoryRepository.findAll();
        List<CategoryResponseDto> tempCategories=generalConverter.convertEntitiesToTargetEntity(categories, CategoryResponseDto.class);
        return tempCategories;
    }

    @Override
    public String saveCategory(CategorySaveRequestDto categorySaveRequestDto) {
        Category tempCategory = generalConverter.convertEntityToTargetEntity(categorySaveRequestDto,Category.class);
        this.categoryRepository.save(tempCategory);
        log.info("Admin yeni bir kategori ekledi: "+tempCategory.getCategoryName());
        return "Kategori başarıyla eklendi";
    }

    @Override
    @CacheEvict("products")
    public String deleteCategory(int categoryId) throws CategoryNotFoundException {
        Category tempCategory = this.categoryRepository
                .findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Böyle bir kategori bulunmamaktadır"));
        categoryRepository.delete(tempCategory);
        log.info("Admin bir kategori sildi: "+tempCategory.getCategoryName());
        return  "Kategori Silme İşlemi Başarıyla Gerçekleştirildi.";
    }



    @Override
    public CategoryResponseDto getCategoryByName(String categoryName) throws CategoryNotFoundException {
        Category tempCategory= categoryRepository.findByCategoryNameIgnoreCase(categoryName).orElseThrow(() -> new CategoryNotFoundException("Böyle bir kategori bulunmamaktadır"));
        return generalConverter.convertEntityToTargetEntity(tempCategory, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto getCategoryByNameContaining(String categoryName) throws CategoryNotFoundException {
        Category tempCategory= categoryRepository.findByCategoryNameContainingIgnoreCase(categoryName).orElseThrow(() -> new CategoryNotFoundException("Böyle bir kategori bulunmamaktadır"));
        return generalConverter.convertEntityToTargetEntity(tempCategory, CategoryResponseDto.class);
    }

    @Override
    public String updateCategory(int categoryId, CategoryUpdateRequestDto categoryUpdateRequestDto) throws CategoryNotFoundException {
        Category tempCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Böyle bir kategori bulunmamaktadır"));
        tempCategory.setCategoryName(categoryUpdateRequestDto.getCategoryName());
        tempCategory.setCategoryImageURL(categoryUpdateRequestDto.getCategoryImageURL());
        categoryRepository.save(tempCategory);
        log.info("Kategori admin tarafından güncellendi: "+tempCategory.getCategoryName());
        return "Kategori Güncelleme İşlemi Başarıyla Gerçekleştirildi.";
    }

}
