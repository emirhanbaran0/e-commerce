package com.innova.ecommerce.entity.dto.response.order;

import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.entity.enums.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {

    @NotNull
    private OrderStatus orderStatus;
    @NotNull
    private String address;
    @Min(0)
    @NotNull
    private Double totalPrice;
}
