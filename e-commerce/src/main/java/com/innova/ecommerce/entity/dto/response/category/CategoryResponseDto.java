package com.innova.ecommerce.entity.dto.response.category;

import com.innova.ecommerce.entity.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CategoryResponseDto {

    @NotBlank
    private String categoryName;
    @NotBlank
    private String categoryImageURL;
}
