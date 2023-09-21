package com.innova.ecommerce.entity.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryUpdateRequestDto {

    @NotBlank
    private String categoryName;
    @NotBlank
    private String categoryImageURL;
}
