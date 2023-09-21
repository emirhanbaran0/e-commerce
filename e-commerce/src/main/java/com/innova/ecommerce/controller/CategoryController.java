package com.innova.ecommerce.controller;

import com.innova.ecommerce.entity.dto.request.category.CategorySaveRequestDto;
import com.innova.ecommerce.entity.dto.request.category.CategoryUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.category.CategoryResponseDto;
import com.innova.ecommerce.exception.CategoryNotFoundException;
import com.innova.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        return new ResponseEntity<>(this.categoryService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDto> getCategoryWithCategoryName(@PathVariable("name") String categoryName) throws CategoryNotFoundException {
        return new ResponseEntity<>(this.categoryService.getCategoryByName(categoryName), HttpStatus.OK);
    }

    @GetMapping("/contains/{name}")
    public ResponseEntity<CategoryResponseDto> getCategoryWithCategoryNameContains(@PathVariable("name") String categoryName) {
        return new ResponseEntity<>(this.categoryService.getCategoryByNameContaining(categoryName), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto){
        return new ResponseEntity<>(categoryService.saveCategory(categorySaveRequestDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable int categoryId, @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto){
        return new ResponseEntity<>(categoryService.updateCategory(categoryId,categoryUpdateRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable int categoryId){

        return new ResponseEntity<>(categoryService.deleteCategory(categoryId),HttpStatus.OK);
    }
}
