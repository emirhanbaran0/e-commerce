package com.innova.ecommerce.service.impl;

import com.innova.ecommerce.config.security.UserInfoUserDetails;
import com.innova.ecommerce.entity.model.User;
import com.innova.ecommerce.exception.UserNotFoundException;
import com.innova.ecommerce.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;



@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;
    private final Logger log = Logger.getLogger(UserInfoUserDetailsService.class.getName());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByUserName(username);
        if(user.isPresent())
            throw   new UsernameNotFoundException("Kullanıcı bulunamamıştır.");
        return user.map(UserInfoUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("Kullanıcı bulunamadı "+ username));
    }
}
