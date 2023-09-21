package com.innova.ecommerce.service;

import com.innova.ecommerce.entity.dto.response.order.OrderResponseDailyReportDto;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseMonthlyReportDto;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseProceedOrdersMonthlyAndYearly;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseYearlyReportDto;
import com.innova.ecommerce.entity.enums.OrderStatus;
import com.innova.ecommerce.entity.model.Order;

import java.util.Date;
import java.util.List;

public interface AdminService {



    List<OrderResponseDailyReportDto> getReportOfOrdersDailyByStatus(Date startDate, OrderStatus orderStatus);

    public List<OrderResponseMonthlyReportDto> getReportOfOrdersMonthlyByStatus(Date startDate, OrderStatus orderStatus);
    public List<OrderResponseYearlyReportDto> getReportOfOrdersYearlyByStatus(Date startDate, OrderStatus orderStatus);

    public List<OrderResponseProceedOrdersMonthlyAndYearly> getHowManyOrderHasBeenProceedYearlyAndMonthly();

}
