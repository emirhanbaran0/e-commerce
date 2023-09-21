package com.innova.ecommerce.entity.dto.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductUpdateRequestDto {

    @NotBlank
    private String productName;
    @Min(0)
    @NotNull
    private double productPrice;
    @NotBlank
    private String productImageUrl;
    @Min(0)
    @NotNull
    private int productQuantity;
    @NotBlank
    private String productDescription;
    @Min(0)
    @NotNull
    private int categoryId;
}
