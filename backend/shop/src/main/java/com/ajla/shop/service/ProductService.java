package com.ajla.shop.service;

import com.ajla.shop.model.Brand;
import com.ajla.shop.model.Category;
import com.ajla.shop.model.PaginationInfo;
import com.ajla.shop.model.Product;
import com.ajla.shop.model.Company;
import com.ajla.shop.repository.BrandRepository;
import com.ajla.shop.repository.CategoryRepository;
import com.ajla.shop.repository.CompanyRepository;
import com.ajla.shop.repository.ProductRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public ProductService(final ProductRepository productRepository,
                          final CategoryRepository categoryRepository,
                          final BrandRepository brandRepository,
                          final CompanyRepository companyRepository) {
        Objects.requireNonNull(productRepository, "productRepository must not be null.");
        Objects.requireNonNull(categoryRepository, "categoryRepository must not be null.");
        Objects.requireNonNull(brandRepository, "brandRepository must not be null.");
        Objects.requireNonNull(companyRepository, "companyRepository must not be null.");
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public PaginationInfo<Product> getNewArrivals(final Long page, final Long size) {
        return productRepository.getAllNewArrivalProducts(page, size);
    }

    @Override
    public Product saveNewProduct(final Product newItem) {
        Category category = categoryRepository.findCategoryByName(newItem.getCategory().getName());
        Category subcategory = categoryRepository.findCategoryByName(newItem.getSubcategory().getName());
        Brand brand = brandRepository.findBrandByName(newItem.getBrand().getName());
        Company company = companyRepository.findCompanyByName(newItem.getCompany().getName());
        newItem.setBrand(brand);
        newItem.setCategory(category);
        newItem.setSubcategory(subcategory);
        newItem.setCompany(company);
        newItem.setDate_published(LocalDateTime.now());

        Map<String, Object> savedItemDetails = new HashMap<>();
        savedItemDetails.put("product_name", newItem.getName());
        savedItemDetails.put("product_price", newItem.getPrice());
        savedItemDetails.put("product_description", newItem.getDescription());
        savedItemDetails.put("product_category", newItem.getCategory().getName());
        savedItemDetails.put("product_subcategory", newItem.getSubcategory().getName());
        savedItemDetails.put("product_brand", newItem.getBrand().getName());
        savedItemDetails.put("product_image_url", newItem.getImage());
        savedItemDetails.put("product_company", newItem.getCompany().getName());

        Map<String, Object> savedItem = new HashMap<>();
        savedItem.put("saved_product", savedItemDetails);

       /* Map<String, Object> items = new HashMap<>();
        items.put(savedItem);*/

        try (FileWriter file = new FileWriter("savedProduct.json")) {
            file.write(String.valueOf(savedItem));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productRepository.save(newItem);
    }
}
