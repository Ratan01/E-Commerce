package com.rk.az.controller;

import com.rk.az.exceptions.ProductException;
import com.rk.az.exceptions.SellerException;
import com.rk.az.model.Product;
import com.rk.az.model.Seller;
import com.rk.az.request.CreateProductRequest;
import com.rk.az.service.ProductService;
import com.rk.az.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers/products")
public class SellerProductController {
    private final ProductService productService;
    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Product>> getProductBySellerId(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller =  sellerService.getSellerProfile(jwt);
        List<Product> products = productService.getProductBySellerId(seller.getId());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request,
                                                 @RequestHeader("Authorization") String jwt) throws SellerException {

        System.out.println("error "+jwt);
        Seller seller = sellerService.getSellerProfile(jwt);

        Product product = productService.createProduct(request, seller);

        //Product product=new Product();

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        try{
            productService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ProductException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product){
        try{
            Product updateproduct = productService.updateProduct(productId, product);
            return new ResponseEntity<>(updateproduct, HttpStatus.OK);
        }
        catch (ProductException p){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
