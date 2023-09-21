package com.innova.ecommerce.service.impl;

import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.entity.enums.DeliveryStatus;
import com.innova.ecommerce.entity.enums.OrderStatus;
import com.innova.ecommerce.entity.model.*;
import com.innova.ecommerce.exception.OrderBasketNotFoundException;
import com.innova.ecommerce.exception.ProductNotFoundException;
import com.innova.ecommerce.exception.UserInsufficientBalanceException;
import com.innova.ecommerce.exception.UserNotFoundException;
import com.innova.ecommerce.repository.*;
import com.innova.ecommerce.service.OrderBasketService;
import com.innova.ecommerce.util.CodeGenerator;
import com.innova.ecommerce.util.GeneralConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderBasketServiceImpl implements OrderBasketService {
    @Autowired
    private final OrderBasketRepository orderBasketRepository;
    private final OrderRepository orderRepository;
    private final GeneralConverter generalConverter;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CodeGenerator codeGenerator;
    private final DeliveryRepository deliveryRepository;
    private final Logger log = Logger.getLogger(OrderBasketServiceImpl.class.getName());

    @Override
    public List<ProductResponseDto> findUserProductsInOrderBasket(int userId,int orderBasketId) {
        userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Sistemde gönderilen id numarasına ait bir kullanıcı bulunamamıştır."));
        OrderBasket orderBasket=orderBasketRepository.findById(orderBasketId).orElseThrow(()-> new OrderBasketNotFoundException("Sepet bulunurken hata oluştu."));
        List<Product>  productList=orderBasket.getProducts();
        return generalConverter.convertEntitiesToTargetEntity(productList, ProductResponseDto.class);
    }

    @Override
    public String addProductToOrderBasket(int userId,int orderBasketId,int productId,int quantity) throws RuntimeException{
        User user=userRepository.findById(userId).
                orElseThrow(()-> new UserNotFoundException("Sistemde gönderilen id numarasına ait bir kullanıcı bulunamamıştır."));
        Product product=productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Aradığınız id numarasına ait bir ürün bulunmamaktadır."));
        OrderBasket orderBasket=orderBasketRepository.findById(orderBasketId).orElseThrow(()-> new OrderBasketNotFoundException("Aradığınız  id numarasına ait bir sepetiniz bulunmamaktadır."));
        List<Product> products=orderBasket.getProducts();
        product.setProductQuantity(product.getProductQuantity()-quantity);
        products.add(product);
        orderBasket.setProducts(products);
        orderBasket.setTotalPrice(orderBasket.getOrderBasketPrice());
        orderBasket.setQuantity(products.size());
        orderBasketRepository.save(orderBasket);
        log.info("Kullanıcı sepete bir ürün ekledi: "+product.getProductName() +"User: "+ user.getUserInfo().getName());
        return "Ürün sepete başarıyla eklendi.";
    }

    @Override
    public String removeProductFromOrderBasket(int userId,int orderBasketId,int productId) throws  RuntimeException{
        User user=userRepository.findById(userId).
                orElseThrow(()-> new UserNotFoundException("Sistemde gönderilen id numarasına ait bir kullanıcı bulunamamıştır."));
        Product product=productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Aradığınız id numarasına ait bir ürün bulunmamaktadır."));
        OrderBasket orderBasket=orderBasketRepository.findById(orderBasketId).orElseThrow(()-> new OrderBasketNotFoundException("Aradığınız  id numarasına ait bir sepetiniz bulunmamaktadır."));
        List<Product> products=orderBasket.getProducts();
        products.remove(product);
        orderBasket.setProducts(products);
        orderBasket.setTotalPrice(orderBasket.getOrderBasketPrice());
        orderBasketRepository.save(orderBasket);
        log.info("Kullanıcı sepetten bir ürün sildi: "+product.getProductName()+ "User: "+ user.getUserInfo().getName());
        return "Ürün sepetten başarıyla silindi.";
    }

    @Override
    public String removeAllProductsFromOrderBasket(int userId,int orderBasketId) throws  RuntimeException{
        User user=userRepository.findById(userId).
                orElseThrow(()-> new UserNotFoundException("Sistemde gönderilen id numarasına ait bir kullanıcı bulunamamıştır."));
         OrderBasket orderBasket=orderBasketRepository.findById(orderBasketId).orElseThrow(()->new OrderBasketNotFoundException("Aradığınız id numarasına ait bir sepet bulunmamaktadır."));
         List<Product> products=new ArrayList<>();
         orderBasket.setProducts(products);
         log.info("Kullanıcı Sepetteki tüm ürünleri kaldırdı. User:" + user.getUserInfo().getName());
         return "Sepet Silme İşlemi Başarıyla Gerçekleştirildi.";
    }

    @Transactional
    public String convertOrderBasketToOrderWithDelivery(int userId,int orderBasketId,String address){
        OrderBasket orderBasket=orderBasketRepository.findById(orderBasketId).orElseThrow(()-> new OrderBasketNotFoundException("Sepet bulunurken hata oluştu."));
        User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı siparişi oluşturulurken hata oluştu"));
        Double orderPrice=orderBasket.getOrderBasketPrice();
        if(!userBudgetControl(user.getBudget(),orderPrice)){
            throw new UserInsufficientBalanceException("Kullanıcının Bakiyesi Yetersiz");
        }
        user.setBudget(user.getBudget()-orderPrice);
        Delivery delivery=new Delivery();
        Order order=new Order(address,OrderStatus.Processing,orderPrice,user,delivery);
        for (int i=0;i<orderBasket.getProducts().size();i++){
            order.getProducts().add(orderBasket.getProducts().get(i));
        }
        delivery.setOrder(order);
        delivery.setStatus(DeliveryStatus.Processing);
        deliveryRepository.save(delivery);
        orderRepository.save(order);
        log.info("Kullanıcı siparişi oluşturuldu. User: " +user.getUserInfo().getName());
        return "Siparişiniz Başarıyla Oluşturuldu. Sipariş Kodunuz: "+ codeGenerator.generateConfirmationCode();
    }
        public boolean userBudgetControl(Double userBudget,Double orderPrice) throws UserInsufficientBalanceException {
            return userBudget >= orderPrice;
        }
}
