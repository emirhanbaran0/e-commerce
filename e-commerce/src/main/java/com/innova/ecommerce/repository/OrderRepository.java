package com.innova.ecommerce.repository;

import com.innova.ecommerce.entity.dto.response.order.OrderResponseProceedOrdersMonthlyAndYearly;
import com.innova.ecommerce.entity.enums.OrderStatus;
import com.innova.ecommerce.entity.model.Order;
import com.innova.ecommerce.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT o.id,o.user.userInfo.name,o.user.userInfo.surname,o.totalPrice from Order o where o.orderStatus= :orderStatus and  o.createDate BETWEEN :startDate AND :endDate")
    List<Order>  findOrdersDaily(Date startDate,Date endDate,OrderStatus orderStatus);

    @Query(value = "SELECT o.id,o.user.userName,o.totalPrice,o.createDate from Order o  where o.orderStatus= :orderStatus and  o.createDate BETWEEN :startDate AND :endDate")
    List<Order>  findOrdersMonthly(Date startDate,Date endDate,OrderStatus orderStatus);

    @Query(value = "SELECT o.id,o.user.userInfo.name,o.user.userInfo.surname,o.totalPrice from Order o where o.orderStatus= :orderStatus and  o.createDate BETWEEN :startDate AND :endDate")
    List<Order>  findOrdersYearly(Date startDate,Date endDate,OrderStatus orderStatus);

    @Query("SELECT YEAR(o.createDate) AS yil, MONTH(o.createDate) AS ay,COUNT(o.id) as toplam_siparis_miktari, SUM(o.totalPrice) AS sepet_tutar " +
            "FROM Order o GROUP BY yil, ay ORDER BY YEAR(o.createDate),MONTH(o.createDate)")
    List<OrderResponseProceedOrdersMonthlyAndYearly> getHowManyOrderHasBeenProceedYearlyAndMonthly();

    List<Order> findOrdersByUser(User user);

    List<Order> findOrdersByUserOrderByOrderStatus(User user);

    List<Order> findOrdersByUserOrderByCreateDate(User user);




}
