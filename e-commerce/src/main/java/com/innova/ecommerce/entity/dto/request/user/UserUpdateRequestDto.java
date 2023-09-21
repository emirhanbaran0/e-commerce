package com.innova.ecommerce.entity.dto.request.user;

import com.innova.ecommerce.entity.dto.request.userInfo.UserInfoUpdateRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequestDto {

    @NotBlank
    private String userName;
    @Email
    private String email;
    @NotBlank
    private UserInfoUpdateRequest userInfo;
}
