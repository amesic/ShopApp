package com.ajla.shop.service;

import com.ajla.shop.model.PaginationInfo;
import com.ajla.shop.model.Product;

import java.util.List;

public interface IProductService {
    PaginationInfo<Product> getNewArrivals(final Long page, final Long size);
}
