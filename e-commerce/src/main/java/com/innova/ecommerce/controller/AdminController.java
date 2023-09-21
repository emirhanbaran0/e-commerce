package com.innova.ecommerce.controller;

import com.innova.ecommerce.entity.dto.response.order.OrderResponseDailyReportDto;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseMonthlyReportDto;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseProceedOrdersMonthlyAndYearly;
import com.innova.ecommerce.entity.dto.response.order.OrderResponseYearlyReportDto;
import com.innova.ecommerce.entity.enums.OrderStatus;
import com.innova.ecommerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/reportOfOrdersDailyByStatus/{date}/{status}")
    public ResponseEntity<List<OrderResponseDailyReportDto>> getReportOfOrdersDailyByStatus(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date date, @PathVariable("status") OrderStatus orderStatus){
        return new  ResponseEntity<>(adminService.getReportOfOrdersDailyByStatus(date,orderStatus), HttpStatus.OK);
    }

    @GetMapping("/reportOfOrdersMonthlyByStatus/{date}/{status}")
    public ResponseEntity<List<OrderResponseMonthlyReportDto>> getReportOfOrdersMonthlyByStatus(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date date, @PathVariable("status") OrderStatus orderStatus){
        return new  ResponseEntity<>(adminService.getReportOfOrdersMonthlyByStatus(date,orderStatus), HttpStatus.OK);
    }

    @GetMapping("/reportOfOrdersYearlyByStatus/{date}/{status}")
    public ResponseEntity<List<OrderResponseYearlyReportDto>> getReportOfOrdersYearlyByStatus(@PathVariable @DateTimeFormat(pattern = "yyyy-mm-dd") Date date, @PathVariable("status") OrderStatus orderStatus){
        return new  ResponseEntity<>(adminService.getReportOfOrdersYearlyByStatus(date,orderStatus), HttpStatus.OK);
    }

    @GetMapping("/HowManyOrderHasBeenProceedYearlyAndMonthly")
    public ResponseEntity<List<OrderResponseProceedOrdersMonthlyAndYearly>> getHowManyOrderHasBeenProceedYearlyAndMonthly(){
        return new ResponseEntity<>(adminService.getHowManyOrderHasBeenProceedYearlyAndMonthly(),HttpStatus.OK);
    }
}
