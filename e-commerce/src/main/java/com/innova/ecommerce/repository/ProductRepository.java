package com.innova.ecommerce.repository;

import com.innova.ecommerce.entity.model.Category;
import com.innova.ecommerce.entity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);

    @Query(value = "SELECT p FROM Product p order by p.productPrice asc")
    List<Product> findProductsAndOrderByProductPriceAsc();

    Product findByProductName(String productName);

}
