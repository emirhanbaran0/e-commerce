package com.innova.ecommerce.repository;

import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.entity.model.OrderBasket;
import com.innova.ecommerce.entity.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderBasketRepository extends JpaRepository<OrderBasket,Integer> {


}
