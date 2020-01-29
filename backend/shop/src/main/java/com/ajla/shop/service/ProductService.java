package com.ajla.shop.service;

import com.ajla.shop.model.PaginationInfo;
import com.ajla.shop.model.Product;
import com.ajla.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository) {
        Objects.requireNonNull(productRepository, "productRepository must not be null.");
        this.productRepository = productRepository;
    }

    @Override
    public PaginationInfo<Product> getNewArrivals(final Long page, final Long size) {
        return productRepository.getAllNewArrivalProducts(page, size);
    }
}
