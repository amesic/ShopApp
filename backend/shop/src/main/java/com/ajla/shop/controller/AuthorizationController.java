package com.ajla.shop.controller;

import com.ajla.shop.model.Product;
import com.ajla.shop.service.CloudinaryService;
import com.ajla.shop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.io.IOException;
import java.util.Objects;

@CrossOrigin(origins = {"http://localhost:4200", "https://shopwisely-app.herokuapp.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final ProductService productService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public AuthorizationController(final ProductService productService,
                                   final CloudinaryService cloudinaryService) {
        Objects.requireNonNull(cloudinaryService, "cloudinaryService must not be null.");
        Objects.requireNonNull(productService, "productService must not be null.");
        this.productService = productService;
        this.cloudinaryService = cloudinaryService;
    }

    @Secured("admin")
    @PostMapping("/product/save")
    public ResponseEntity<?> saveNewProduct(@RequestBody final Product product) {
        try {
            String url = cloudinaryService.saveItemImage(product.getImage());
            product.setImage(url);
        } catch (IOException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            return new ResponseEntity<>(productService.saveNewProduct(product), HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
