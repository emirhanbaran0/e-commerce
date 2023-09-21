package com.innova.ecommerce.entity.dto.request.orderBasket;

import com.innova.ecommerce.entity.model.Product;
import com.innova.ecommerce.entity.model.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderBasketSaveRequestDto {
    @Min(0)
    @NotNull
    private int quantity;
    @Min(0)
    @NotNull
    private Double totalPrice;
    private List<Product> products;
    private User user;

}
