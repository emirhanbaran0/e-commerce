package com.innova.ecommerce.service;

import com.innova.ecommerce.entity.dto.response.orderBasket.OrderBasketResponseDto;
import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.entity.model.OrderBasket;
import com.innova.ecommerce.entity.model.User;
import com.innova.ecommerce.exception.ProductNotFoundException;

import java.util.List;

public interface OrderBasketService {

    List<ProductResponseDto> findUserProductsInOrderBasket(int userId,int orderBasketId);

    public String addProductToOrderBasket(int userId,int orderBasketId,int productId,int quantity) throws RuntimeException;

    public String removeProductFromOrderBasket(int userId,int orderBasketId,int productId) throws  RuntimeException;

    public String removeAllProductsFromOrderBasket(int userId,int orderBasketId);

    public String convertOrderBasketToOrderWithDelivery(int orderBasketId,int userId,String address);
}
