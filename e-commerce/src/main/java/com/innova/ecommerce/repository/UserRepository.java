package com.innova.ecommerce.repository;

import com.innova.ecommerce.entity.model.User;
import com.innova.ecommerce.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByUserName(String userName) throws UserNotFoundException;

}
