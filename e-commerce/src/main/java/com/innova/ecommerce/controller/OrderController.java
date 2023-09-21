package com.innova.ecommerce.controller;

import com.innova.ecommerce.entity.dto.response.order.OrderResponseDto;
import com.innova.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

        private  final OrderService orderService;

        @GetMapping("/{userId}/allOrders")
        public ResponseEntity<List<OrderResponseDto>> getAllOrdersByUser(@PathVariable("userId") Integer userId){
            return new ResponseEntity<>(orderService.getAllOrdersByUser(userId), HttpStatus.OK);
        }

        @GetMapping("/{userId}/allOrdersOrderByDate")
        public ResponseEntity<List<OrderResponseDto>> getAllOrdersByUserOrderByDate(@PathVariable("userId") Integer userId){
            return new ResponseEntity<>(orderService.getAllOrdersByUserOrderByDate(userId), HttpStatus.OK);
        }

        @GetMapping("/{userId}/allOrdersOrderByStatus")
        public ResponseEntity<List<OrderResponseDto>> getAllOrdersByUserOrderByStatus(@PathVariable("userId") Integer userId){
        return new ResponseEntity<>(orderService.getAllOrdersByUserOrderByStatus(userId), HttpStatus.OK);
        }

        @PutMapping("/orderToDelivery/{orderId}/{userId}/")
        public ResponseEntity<String> convertOrderToDelivery(@PathVariable("orderId") Integer orderId,@PathVariable("userId") Integer userId){
            return  new ResponseEntity<>(orderService.convertOrderToOrderDelivery(orderId,userId),HttpStatus.OK);
        }

        @PutMapping("/{orderId}/{userId}/cancelOrder")
        public ResponseEntity<String> cancelOrder(@PathVariable("orderId") Integer orderId,@PathVariable("userId") Integer userId){
            return new ResponseEntity<>(orderService.cancelOrder(userId,orderId),HttpStatus.OK);
        }
}
