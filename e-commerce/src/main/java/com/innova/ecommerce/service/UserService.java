package com.innova.ecommerce.service;

import com.innova.ecommerce.entity.dto.request.card.CardAddBudgetRequestDto;
import com.innova.ecommerce.entity.dto.request.user.UserSaveRequestDto;
import com.innova.ecommerce.entity.dto.request.user.UserUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.user.UserResponseDto;
import com.innova.ecommerce.entity.model.Card;
import com.innova.ecommerce.exception.UserNotFoundException;
import com.innova.ecommerce.exception.UserPasswordNotMatchedException;

public interface UserService {

    public String addUser(UserSaveRequestDto userSaveRequestDto);

    String deleteUser(Integer userId, String password) throws RuntimeException;

    public String addProductToFavoriteList(Integer userId, Integer productId);

    public String addBudgetToUser(Integer userId, CardAddBudgetRequestDto card,Double amount) throws  UserNotFoundException;
    public String updateUserInfo(Integer userId, UserUpdateRequestDto userUpdateRequestDto) throws UserNotFoundException;
    public UserResponseDto getUserById(Integer userId) throws UserNotFoundException;

    public String updateUserPassword(Integer userId,String oldPassword,String newPassword) throws UserPasswordNotMatchedException;
}
