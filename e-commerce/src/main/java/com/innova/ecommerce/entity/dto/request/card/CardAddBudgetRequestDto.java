package com.innova.ecommerce.entity.dto.request.card;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CardAddBudgetRequestDto {
    @NotBlank
    private String cardNumber;
    @NotBlank
    private String cardOwnerName;
    @NotBlank
    private String cardOwnerSurname;
    @NotBlank
    private String expiredDate;
    @NotBlank
    private String CVCode;
    @NotBlank
    private Double budget;

}
