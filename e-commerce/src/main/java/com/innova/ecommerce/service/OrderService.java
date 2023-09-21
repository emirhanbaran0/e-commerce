package com.innova.ecommerce.service;

import com.innova.ecommerce.entity.dto.response.order.OrderResponseDto;
import com.innova.ecommerce.entity.enums.OrderStatus;
import com.innova.ecommerce.entity.model.Order;
import com.innova.ecommerce.entity.model.OrderBasket;
import com.innova.ecommerce.entity.model.User;
import com.innova.ecommerce.exception.OrderNotFoundException;
import com.innova.ecommerce.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {


    List<OrderResponseDto> getAllOrdersByUser(Integer userId) throws UserNotFoundException;
    List<OrderResponseDto> getAllOrdersByUserOrderByDate(Integer userId) throws UserNotFoundException;
    List<OrderResponseDto> getAllOrdersByUserOrderByStatus(Integer userId) throws UserNotFoundException;


    OrderResponseDto getOrderByUser(Integer userId, Integer orderId) throws UserNotFoundException;

    public String convertOrderToOrderDelivery(Integer orderId, Integer userId) throws RuntimeException;

    public String cancelOrder(Integer userId,Integer orderId) throws RuntimeException;

}
