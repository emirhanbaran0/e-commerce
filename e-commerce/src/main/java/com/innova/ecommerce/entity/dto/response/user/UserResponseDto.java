package com.innova.ecommerce.entity.dto.response.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    @NotBlank
    String userName;
    @NotBlank
    String password;
    @Email
    String email;
    @NotBlank
    String name;
    @NotBlank
    String surName;
    @NotBlank
    String phoneNumber;
    @Min(0)
    @NotNull
    Double budget;
}
