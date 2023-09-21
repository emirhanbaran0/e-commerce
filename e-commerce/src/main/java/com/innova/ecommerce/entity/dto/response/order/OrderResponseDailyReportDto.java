package com.innova.ecommerce.entity.dto.response.order;

import com.innova.ecommerce.entity.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDailyReportDto {

    @NotBlank
    String userName;
    @Min(0)
    @NotNull
    Double totalPrice;
    @NotNull
    Date createDate;
    List<Product> products;
}
