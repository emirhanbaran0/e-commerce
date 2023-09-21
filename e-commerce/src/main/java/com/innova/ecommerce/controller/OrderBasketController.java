package com.innova.ecommerce.controller;


import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.service.OrderBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderBasket")
@RequiredArgsConstructor
public class OrderBasketController {

    private final OrderBasketService orderBasketService;

    @GetMapping("/{userId}/{orderBasketId}")
    public ResponseEntity<List<ProductResponseDto>> getUserProductsInOrderBasket(@PathVariable("userId") Integer userId,@PathVariable("orderBasketId") Integer orderBasketId){
        return new ResponseEntity<>(this.orderBasketService.findUserProductsInOrderBasket(userId,orderBasketId), HttpStatus.OK);
    }

    @PutMapping("/addProduct/{userId}/{orderBasketId}/{productId}/{quantity}")
    public ResponseEntity<String> addProductToOrderBasket(@PathVariable("userId") Integer userId,@PathVariable("orderBasketId") Integer orderBasketId,@PathVariable("productId") Integer productId,Integer quantity){

        return new ResponseEntity<>(this.orderBasketService.addProductToOrderBasket(userId,orderBasketId,productId,quantity), HttpStatus.OK);
    }

    @PutMapping("/removeProduct/{userId}/{orderBasketId}/{productId}/")
    public ResponseEntity<String> removeProductFromOrderBasket(@PathVariable("userId") Integer userId,@PathVariable("orderBasketId") Integer orderBasketId,@PathVariable("productId") Integer productId){
        return new ResponseEntity<>(this.orderBasketService.removeProductFromOrderBasket(userId,orderBasketId,productId),HttpStatus.OK);
    }

    @PostMapping("/{userId}/{orderBasketId}")
    public ResponseEntity<String> convertOrderBasketToOrderWithDelivery(@PathVariable("userId") Integer userId,@PathVariable("orderBasketId") Integer orderBasketId,@RequestParam("address") String address){
        return new ResponseEntity<>(this.orderBasketService.convertOrderBasketToOrderWithDelivery(userId,orderBasketId,address),HttpStatus.OK);
    }
}
