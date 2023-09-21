package com.innova.ecommerce.service;

import com.innova.ecommerce.entity.dto.request.product.ProductSaveRequestDto;
import com.innova.ecommerce.entity.dto.request.product.ProductUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.entity.model.Product;
import com.innova.ecommerce.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public List<ProductResponseDto> getAllProducts() throws ProductNotFoundException;

    List<ProductResponseDto> getProductsAndOrderByProductPriceAsc();

    public List<ProductResponseDto> getProductsByCategory(Integer categoryId);

    public String saveProduct(ProductSaveRequestDto productSaveRequestDto);

    public ProductResponseDto getProductById(Integer productId) throws ProductNotFoundException;

    public String deleteProduct(Integer  productId) throws ProductNotFoundException;

    public String updateProduct(Integer productId, ProductUpdateRequestDto productUpdateRequestDto) throws ProductNotFoundException;

    //String checkUnique(Integer id, String title);
}
