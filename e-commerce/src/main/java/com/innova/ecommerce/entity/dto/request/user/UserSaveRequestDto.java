package com.innova.ecommerce.entity.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSaveRequestDto {
    @NotBlank
    String userName;
    @NotBlank
    String password;
    @Email
    String email;
    @NotBlank
    String name;
    @NotBlank
    String surname;
    @NotBlank
    String phoneNumber;
}
