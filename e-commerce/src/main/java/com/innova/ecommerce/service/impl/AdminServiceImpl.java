package com.innova.ecommerce.service.impl;

import com.innova.ecommerce.entity.dto.response.order.OrderResponseDailyReportDto;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseMonthlyReportDto;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseProceedOrdersMonthlyAndYearly;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseYearlyReportDto;
import com.innova.ecommerce.entity.enums.OrderStatus;
import com.innova.ecommerce.entity.model.Order;
import com.innova.ecommerce.exception.OrderBasketNotFoundException;
import com.innova.ecommerce.repository.OrderRepository;
import com.innova.ecommerce.repository.ProductRepository;
import com.innova.ecommerce.service.AdminService;
import com.innova.ecommerce.service.UserService;
import com.innova.ecommerce.util.GeneralConverter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private  final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final GeneralConverter generalConverter;
    private final UserService userService;
    private final Logger log = Logger.getLogger(AdminServiceImpl.class.getName());

    @Override
    public List<OrderResponseDailyReportDto> getReportOfOrdersDailyByStatus(Date startDate, OrderStatus orderStatus) {
        Date finishDate= DateUtils.addMonths(startDate, -1);
        List<Order> orders=orderRepository.findOrdersMonthly(startDate,finishDate,orderStatus);
        for(int i=0;i<orders.size();i++){
            Order order=orderRepository.findById(orders.get(i).getId())
                    .orElseThrow(()-> new OrderBasketNotFoundException("Sipariş bulunurken hata ile karşılaşıldı"));
            orders.get(i).setProducts(order.getProducts());
        }
        log.info("Admin tarafından günlük rapor alındı.");
        return generalConverter.convertEntitiesToTargetEntity(orderRepository.findOrdersMonthly(startDate,finishDate,orderStatus),OrderResponseDailyReportDto.class);
    }

    @Override
    public List<OrderResponseMonthlyReportDto> getReportOfOrdersMonthlyByStatus(Date startDate, OrderStatus orderStatus) {
        Date finishDate= DateUtils.addMonths(startDate, -1);
        List<Order> orders=orderRepository.findOrdersMonthly(startDate,finishDate,orderStatus);
        for(int i=0;i<orders.size();i++){
            Order order=orderRepository.findById(orders.get(i).getId())
                    .orElseThrow(()-> new OrderBasketNotFoundException("Sipariş bulunurken hata ile karşılaşıldı"));
            orders.get(i).setProducts(order.getProducts());
        }
        log.info("Admin tarafından aylık rapor alındı.");
        return generalConverter.convertEntitiesToTargetEntity(orderRepository.findOrdersMonthly(startDate,finishDate,orderStatus),OrderResponseMonthlyReportDto.class);
    }

    //Duplicate mi acaba?

    @Override
    public List<OrderResponseYearlyReportDto> getReportOfOrdersYearlyByStatus(Date startDate, OrderStatus orderStatus) {
        Date finishDate= DateUtils.addYears(startDate, -1);
        List<Order> orders=orderRepository.findOrdersMonthly(startDate,finishDate,orderStatus);
        for(int i=0;i<orders.size();i++){
            Order order=orderRepository.findById(orders.get(i).getId())
                    .orElseThrow(()-> new OrderBasketNotFoundException("Sipariş bulunurken hata ile karşılaşıldı"));
            orders.get(i).setProducts(order.getProducts());
        }
        log.info("Admin tarafından yıllık rapor alındı.");
        return generalConverter.convertEntitiesToTargetEntity(orderRepository.findOrdersYearly(startDate,finishDate,orderStatus),OrderResponseYearlyReportDto.class);
    }

    @Override
    public List<OrderResponseProceedOrdersMonthlyAndYearly> getHowManyOrderHasBeenProceedYearlyAndMonthly() {
        log.info("Admin sipariş istatistiklerini görüntüledi.");
        return orderRepository.getHowManyOrderHasBeenProceedYearlyAndMonthly();
    }




}
