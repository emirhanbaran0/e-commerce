package com.innova.ecommerce.service.impl;

import com.innova.ecommerce.entity.dto.request.product.ProductSaveRequestDto;
import com.innova.ecommerce.entity.dto.request.product.ProductUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.entity.model.Category;
import com.innova.ecommerce.entity.model.Product;
import com.innova.ecommerce.exception.CategoryNotFoundException;
import com.innova.ecommerce.exception.ProductNotFoundException;
import com.innova.ecommerce.repository.CategoryRepository;
import com.innova.ecommerce.repository.ProductRepository;
import com.innova.ecommerce.service.ProductService;
import com.innova.ecommerce.util.GeneralConverter;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@EnableCaching
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final GeneralConverter generalConverter;
    private final CategoryRepository categoryRepository;
    private final Logger log = Logger.getLogger(ProductServiceImpl.class.getName());


    @Override
    @Cacheable(value = "products")
    public List<ProductResponseDto> getAllProducts() {
        return generalConverter.convertEntitiesToTargetEntity(productRepository.findAll(),ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getProductsAndOrderByProductPriceAsc() {
        return generalConverter.convertEntitiesToTargetEntity(productRepository.findProductsAndOrderByProductPriceAsc(),ProductResponseDto.class);
    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(Integer categoryId) {
        Category tempCategory = this.categoryRepository.findById(categoryId).orElseThrow(()-> new RuntimeException("Kategori bulunamadı!"));
        return this.generalConverter
                .convertEntitiesToTargetEntity(this.productRepository.findByCategory(tempCategory),ProductResponseDto.class);
    }

    /*@Override
    public List<ProductResponseDto> getProductsWithOrder(Integer orderId) {
        List<Product> products = this.productRepository.findProductsByOrderId(orderId);
        List<ProductResponseDto> resultList = this.generalConverter.convertEntitiesToTargetEntity(products,ProductResponseDto.class);
        return resultList;
    }*/

    @Override
    @CachePut(value = "products")
    public String saveProduct(ProductSaveRequestDto productSaveRequestDto) {
        Product tempProduct = new Product();
        tempProduct.setProductName(productSaveRequestDto.getProductName());
        tempProduct.setProductPrice(productSaveRequestDto.getProductPrice());
        tempProduct.setProductDescription(productSaveRequestDto.getProductDescription());
        tempProduct.setProductQuantity(productSaveRequestDto.getProductQuantity());
        tempProduct.setProductImageURL(productSaveRequestDto.getProductImageUrl());
        tempProduct.setCategory(
                categoryRepository.findByCategoryNameIgnoreCase(productSaveRequestDto.getCategoryName()).orElseThrow( () -> new CategoryNotFoundException("Kategori bulunamadı!"))
        );
        productRepository.save(tempProduct);
        log.info("Admin tarafınfan "+tempProduct.getId()+ " id'li ürün eklendi ");
        return  "Ürün Başarıyla Kaydedildi";
    }

    @Override
    public ProductResponseDto getProductById(Integer productId) throws ProductNotFoundException {
        return generalConverter.convertEntityToTargetEntity(productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Aradığınız ürün numarasına ait bir ürün bulunmamaktadır.")),ProductResponseDto.class);
    }

    @Override
    @CachePut("products")
    public String deleteProduct(Integer productId) throws ProductNotFoundException {
        Product tempProduct = this.productRepository.findById(productId).orElseThrow(()->  new ProductNotFoundException("Aradığınız id numarasına ait bir ürün bulunmamaktadır."));
        productRepository.delete(tempProduct);
        log.info("Admin tarafınfan "+productId+ " id'li ürün silindi ");
        return  productId+" Id'li Ürün Başarıyla Silindi.";
    }

    @Override
    @CachePut("products")
    public String updateProduct(Integer productId, ProductUpdateRequestDto productUpdateRequestDto) throws ProductNotFoundException {
        Product tempProduct = this.productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Aradığınız id numarasına ait bir ürün bulunmamaktadır."));
        tempProduct.setProductName(productUpdateRequestDto.getProductName());
        tempProduct.setProductPrice(productUpdateRequestDto.getProductPrice());
        tempProduct.setProductDescription(productUpdateRequestDto.getProductDescription());
        tempProduct.setProductQuantity(productUpdateRequestDto.getProductQuantity());
        this.productRepository.save(tempProduct);

        ProductResponseDto tempProductResponseDto = this.generalConverter
                .convertEntityToTargetEntity(tempProduct,ProductResponseDto.class);
        log.info("Admin tarafınfan "+productId+ " id'li ürün güncellendi ");
        return productId+" Id'li Ürün Başarıyla Güncellendi";
    }
}
