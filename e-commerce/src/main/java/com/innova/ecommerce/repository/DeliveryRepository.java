package com.innova.ecommerce.repository;

import com.innova.ecommerce.entity.model.Delivery;
import com.innova.ecommerce.entity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    Delivery findByOrder(Order order);
}
