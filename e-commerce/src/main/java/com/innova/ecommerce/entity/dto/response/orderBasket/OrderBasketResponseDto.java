package com.innova.ecommerce.entity.dto.response.orderBasket;

import com.innova.ecommerce.entity.model.Product;
import com.innova.ecommerce.entity.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderBasketResponseDto {

    private int quantity;
    private Double totalPrice;
    private List<Product> products;


}
