package com.innova.ecommerce.repository;

import com.innova.ecommerce.entity.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
}
