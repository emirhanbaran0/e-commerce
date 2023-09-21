package com.innova.ecommerce.service.impl;

import com.innova.ecommerce.entity.dto.request.card.CardAddBudgetRequestDto;
import com.innova.ecommerce.entity.dto.request.user.UserSaveRequestDto;
import com.innova.ecommerce.entity.dto.request.user.UserUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.user.UserResponseDto;
import com.innova.ecommerce.entity.enums.UserRole;
import com.innova.ecommerce.entity.model.OrderBasket;
import com.innova.ecommerce.entity.model.Product;
import com.innova.ecommerce.entity.model.User;
import com.innova.ecommerce.entity.model.UserInfo;
import com.innova.ecommerce.exception.ProductNotFoundException;
import com.innova.ecommerce.exception.UserNotFoundException;
import com.innova.ecommerce.exception.UserPasswordNotMatchedException;
import com.innova.ecommerce.repository.OrderBasketRepository;
import com.innova.ecommerce.repository.ProductRepository;
import com.innova.ecommerce.repository.UserRepository;
import com.innova.ecommerce.service.UserService;
import com.innova.ecommerce.util.GeneralConverter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final GeneralConverter generalConverter;
    private final ProductRepository productRepository;
    private final OrderBasketRepository orderBasketRepository;
    private final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

    @Override
    public String addUser(UserSaveRequestDto userSaveRequestDto) {
        String userName=userSaveRequestDto.getUserName();
        String password=userSaveRequestDto.getPassword();
        String email=userSaveRequestDto.getEmail();
        Double budget=0.0;
        UserInfo userInfo=new UserInfo();
        UserRole userRole=UserRole.USER;
        User tempUser=new User(userName,password,email,userRole,budget,userInfo);
        userInfo.setName(userSaveRequestDto.getName());
        userInfo.setSurname(userSaveRequestDto.getSurname());
        userInfo.setPhoneNumber(userSaveRequestDto.getPhoneNumber());
        userInfo.setUser(tempUser);
        userRepository.save(tempUser);
        OrderBasket orderBasket=new OrderBasket(tempUser);
        orderBasketRepository.save(orderBasket);
        return "Kullanıcı Başarıyla kaydedildi.";
    }

    public Authentication getAuth(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return  auth;
    }
    @Override
    public String deleteUser(Integer userId, String password) throws RuntimeException {
        User tempUser=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunamadı"));
        if(!tempUser.getPassword().equals(password))
            throw new UserPasswordNotMatchedException("Girdiğiniz şifre yanlıştır.Lütfen tekrar deneyiniz");

        userRepository.deleteById(userId);
        log.info("Kullanıcı hesabini sildi. User: "+ tempUser.getUserName() );
        return  userId+" Id'li kullanıcı başarıyla silindi.";
    }

    public UserResponseDto getUserById(Integer userId) throws  UserNotFoundException{
        User  tempUser= userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("Kullanıcı Bulunurken hatayla karşılaşıldı"));
        UserResponseDto userResponseDto=new UserResponseDto(tempUser.getUserName(),tempUser.getPassword(),tempUser.getEmail(),tempUser.getUserInfo().getName(),tempUser.getUserInfo().getSurname(),tempUser.getUserInfo().getPhoneNumber(),tempUser.getBudget());
        return userResponseDto;
    }

    @Override
    public String addProductToFavoriteList(Integer userId, Integer productId) throws RuntimeException{
        User tempUser=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunamadı"));
        Product product=productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Ürün bulunurken hatayla karşılaşıldı"));
        Set<Product> favoriteProduct=tempUser.getUserFavoriteProducts();
        favoriteProduct.add(product);
        tempUser.setUserFavoriteProducts(favoriteProduct);
        System.out.println(tempUser.getUserFavoriteProducts().toString());
        log.info("Kullanıcı favorilerine "+ productId+ " id'li ürünü ekledi. User: "+ tempUser.getUserName());
        return "Ürün başarıyla favorilere kaydedildi.";
    }

    @Override
    @Transactional
    public String addBudgetToUser(Integer userId, CardAddBudgetRequestDto card,Double amount) throws  UserNotFoundException{
        User tempUser=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunamadı"));
        //Kart Doğruluk kontrolü yapılır..Eğer doğruysa ve kart içinddeki bakiye yeterliyse
        card.setBudget(card.getBudget()-amount);
        tempUser.setBudget(tempUser.getBudget()+amount);
        log.info("Kullanıcı bakiyesine ekleme yaptı. User: "+ tempUser.getUserName());
        return "Bakiyenize "+ amount +"TL Ekleme yapıldı.Güncel Bakiyeniz: "+tempUser.getBudget();
    }

    public String updateUserInfo(Integer userId,UserUpdateRequestDto userUpdateRequestDto) throws UserNotFoundException{
        User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hatayla karşılaşıldı."));
        user.setUserName(userUpdateRequestDto.getUserName());
        user.setEmail(userUpdateRequestDto.getEmail());
        user.getUserInfo().setName(userUpdateRequestDto.getUserInfo().getName());
        user.getUserInfo().setSurname(userUpdateRequestDto.getUserInfo().getSurname());
        user.getUserInfo().setPhoneNumber(userUpdateRequestDto.getUserInfo().getPhoneNumber());
        userRepository.save(user);
        log.info("Kullanıcı bilgilerini güncelledi. User: "+ user.getUserName());
        return  "Kullanıcı bilgileri başarıyla güncellendi.";
    }

    public String updateUserPassword(Integer userId,String oldPassword,String newPassword) throws RuntimeException{
        User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("Kullanıcı bulunurken hatayla karşılaşıldı."));
        if(!user.getPassword().equals(oldPassword))
           throw  new  UserPasswordNotMatchedException("Girdiğiniz eski şifreniz yanlıştır.Lütfen tekrar deneyiniz");
        user.setPassword(newPassword);
        log.info("Kullanıcı şifresini güncelledi. User: "+ user.getUserName());
        return "Şifre başarıyla değiştirildi";
    }


}
