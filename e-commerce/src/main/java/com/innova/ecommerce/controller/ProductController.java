package com.innova.ecommerce.controller;

import com.innova.ecommerce.entity.dto.request.product.ProductSaveRequestDto;
import com.innova.ecommerce.entity.dto.request.product.ProductUpdateRequestDto;
import com.innova.ecommerce.entity.dto.response.product.ProductResponseDto;
import com.innova.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private  final ProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/orderByProductPriceAsc")
    ResponseEntity<List<ProductResponseDto>> getProductsAndOrderByProductPriceAsc(){
        return new ResponseEntity<>(productService.getProductsAndOrderByProductPriceAsc(),HttpStatus.OK);
    }

    @GetMapping("categoryProducts/{categoryId}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<>(productService.getProductsByCategory(categoryId),HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(Integer productId){
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody ProductSaveRequestDto productSaveRequestDto){
        return new ResponseEntity<>(productService.saveProduct(productSaveRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Integer productId){
        return new ResponseEntity<>(productService.deleteProduct(productId),HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer productId,@RequestBody ProductUpdateRequestDto productUpdateRequestDto){
        return new ResponseEntity<>(productService.updateProduct(productId,productUpdateRequestDto),HttpStatus.OK);
    }

}
