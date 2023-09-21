package com.innova.ecommerce.controller;

import com.innova.ecommerce.entity.dto.request.card.CardAddBudgetRequestDto;
import com.innova.ecommerce.entity.dto.request.user.UserSaveRequestDto;
import com.innova.ecommerce.entity.dto.request.user.UserUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.user.UserResponseDto;
import com.innova.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserSaveRequestDto userSaveRequestDto){
        return new ResponseEntity<>(userService.addUser(userSaveRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId, @RequestParam("password") String password){
        return new ResponseEntity<>(userService.deleteUser(userId,password),HttpStatus.OK);
    }

    @PutMapping("/{userId}/addToFavoriteList/{productId}")
    public ResponseEntity<String> addProductToFavoriteList(@PathVariable("userId") Integer userId,@PathVariable("productId") Integer productId){
        return new ResponseEntity<>(userService.addProductToFavoriteList(userId,productId),HttpStatus.OK);
    }

    @PutMapping("/{userId}/addBudget")
    public ResponseEntity<String> addBudgetToUser(@PathVariable("userId") Integer userId,@RequestBody CardAddBudgetRequestDto cardAddBudgetRequestDto,@RequestParam("amount") Double amount){
        return new ResponseEntity<>(userService.addBudgetToUser(userId,cardAddBudgetRequestDto,amount),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> userUpdateInfo(@PathVariable("userId") Integer userId,@RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return new ResponseEntity<>(userService.updateUserInfo(userId,userUpdateRequestDto),HttpStatus.OK);
    }

    @PutMapping("/{userId}/userUpdatePassword")
    public ResponseEntity<String> userUpdatePassword(@PathVariable("userId") Integer userId,@RequestParam String oldPassword,@RequestParam String newPassword){
        return new ResponseEntity<>(userService.updateUserPassword(userId,oldPassword,newPassword),HttpStatus.OK);
    }

}
