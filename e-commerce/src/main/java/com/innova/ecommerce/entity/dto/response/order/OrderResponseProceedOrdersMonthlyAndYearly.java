package com.innova.ecommerce.entity.dto.response.order;


import com.innova.ecommerce.entity.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class OrderResponseProceedOrdersMonthlyAndYearly {

    @NotBlank
    String userName;
    @Min(0)
    @NotNull
    Double totalPrice;
    @NotNull
    Date createDate;
    List<Product> products;

}
