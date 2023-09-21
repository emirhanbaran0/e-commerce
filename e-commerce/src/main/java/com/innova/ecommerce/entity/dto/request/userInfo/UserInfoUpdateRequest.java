package com.innova.ecommerce.entity.dto.request.userInfo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInfoUpdateRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String phoneNumber;

}
