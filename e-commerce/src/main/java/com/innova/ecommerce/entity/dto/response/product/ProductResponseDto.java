package com.innova.ecommerce.entity.dto.response.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductResponseDto {

    @NotBlank
    private String productName;
    @Min(0)
    @NotNull
    private double productPrice;
    @NotBlank
    private String productImageUrl;

}
