package com.ajla.shop.controller;

import com.ajla.shop.model.PaginationInfo;
import com.ajla.shop.model.Product;
import com.ajla.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200", "https://shopwisely-app.herokuapp.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        Objects.requireNonNull(productService, "productService must not be null.");
        this.productService = productService;
    }

    @GetMapping("/product/newArrivals")
    public ResponseEntity<PaginationInfo<Product>> getNewArrivals(
            @RequestParam("page") final Long page,
            @RequestParam("size") final Long size) {
        return new ResponseEntity<>(productService.getNewArrivals(page, size), HttpStatus.OK);
    }

}
