package com.rk.az.service;

import com.rk.az.exceptions.ProductException;
import com.rk.az.model.Product;
import com.rk.az.model.Seller;
import com.rk.az.request.CreateProductRequest;

import org.springframework.data.domain.Page;


import java.awt.print.Pageable;
import java.util.List;


public interface ProductService {
    Product createProduct(CreateProductRequest req, Seller seller);
    void deleteProduct(Long productId) throws ProductException;
    Product updateProduct(Long productId, Product product) throws ProductException;
    Product findProductById(Long productId) throws ProductException;
    List<Product> searchProducts(String query);
    public Page<Product> getAllProducts(
            String category,
            String brand,
            String colors,
            String sizes,
            Integer minPrice,
            Integer maxPrice,
            Integer minDiscount,
            String sort,
            String stock,
            Integer pageNumber
    );
    List<Product> getProductBySellerId(Long sellerId);

}
