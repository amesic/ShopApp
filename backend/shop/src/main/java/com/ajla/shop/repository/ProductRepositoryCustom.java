package com.ajla.shop.repository;

import com.ajla.shop.model.PaginationInfo;
import com.ajla.shop.model.Product;

public interface ProductRepositoryCustom {
    PaginationInfo<Product> getAllNewArrivalProducts(final Long page, final Long size);
}
