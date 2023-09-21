package com.innova.ecommerce.service.impl;

import com.innova.ecommerce.entity.dto.response.order.OrderResponseDto;
import com.innova.ecommerce.entity.enums.DeliveryStatus;
import com.innova.ecommerce.entity.enums.OrderStatus;
import com.innova.ecommerce.entity.model.Delivery;
import com.innova.ecommerce.entity.model.Order;
import com.innova.ecommerce.entity.model.User;
import com.innova.ecommerce.exception.OrderNotFoundException;
import com.innova.ecommerce.exception.UserNotFoundException;
import com.innova.ecommerce.repository.DeliveryRepository;
import com.innova.ecommerce.repository.OrderRepository;
import com.innova.ecommerce.repository.UserRepository;
import com.innova.ecommerce.service.OrderService;
import com.innova.ecommerce.util.CodeGenerator;
import com.innova.ecommerce.util.GeneralConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

        private final OrderRepository orderRepository;
        private final UserRepository userRepository;
        private final CodeGenerator codeGenerator;
        private final GeneralConverter generalConverter;
        private final DeliveryRepository deliveryRepository;
        private final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

        @Override
        public List<OrderResponseDto> getAllOrdersByUser(Integer userId) throws UserNotFoundException {
                User tempUser=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hatayla karşılaşıldı."));
                List<Order> orders=orderRepository.findOrdersByUser(tempUser);
                return generalConverter.convertEntitiesToTargetEntity(orders,OrderResponseDto.class);
        }

        public List<OrderResponseDto> getAllOrdersByUserOrderByDate(Integer userId) throws UserNotFoundException {
                User tempUser=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hatayla karşılaşıldı."));
                List<Order> orders=orderRepository.findOrdersByUserOrderByCreateDate(tempUser);

                return generalConverter.convertEntitiesToTargetEntity(orders,OrderResponseDto.class);
        }

        @Override
        public List<OrderResponseDto> getAllOrdersByUserOrderByStatus(Integer userId) throws UserNotFoundException  {
                User tempUser=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hatayla karşılaşıldı."));
                List<Order> orders=orderRepository.findOrdersByUserOrderByOrderStatus(tempUser);
                return generalConverter.convertEntitiesToTargetEntity(orders,OrderResponseDto.class);
        }
        @Override
        public OrderResponseDto getOrderByUser(Integer userId, Integer orderId) throws UserNotFoundException {
                User tempUser=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hatayla karşılaşıldı."));
                Order order=orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Sepet bulunurken hatayla karşılaşıldı."));
                return generalConverter.convertEntityToTargetEntity(order,OrderResponseDto.class);
        }

        @Override
        @Transactional
        public String convertOrderToOrderDelivery(Integer orderId,Integer userId) throws RuntimeException {
                User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hata ile karşılaşıldı."));
               Order order= orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Sepet bulunurken hata ile karşılaşıldı."));
                String deliveryCode= codeGenerator.generateConfirmationCode();
                order.setOrderStatus(OrderStatus.Paid);
                user.setBudget(user.getBudget()-order.getTotalPrice());
                Delivery delivery=deliveryRepository.findByOrder(order);
                delivery.setDeliveryCode(deliveryCode);
                delivery.setStatus(DeliveryStatus.Processing);
                log.info("Kullanıcı siparişi kargoya verildi. User: "+user.getUserInfo().getName());
                return "Siparişiniz Kargoya verilmiştir. Kargo numarası: "+deliveryCode + " ile takip edebilirsiniz.";
        }

        @Override
        public String cancelOrder(Integer userId, Integer orderId) throws RuntimeException {
                User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hata ile karşılaşıldı."));
                Order order= orderRepository.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Sepet bulunurken hata ile karşılaşıldı."));
                order.setOrderStatus(OrderStatus.Canceled);
                order.getDelivery().setStatus(DeliveryStatus.Canceled);
                orderRepository.save(order);
                log.info("Kullanıcı "+ order.getId()+ " id'li siparişini iptal etti. User: "+user.getUserInfo().getName());
                return "Siparişiniz Başarıyla İptal Edilmiştir.";
        }
}
