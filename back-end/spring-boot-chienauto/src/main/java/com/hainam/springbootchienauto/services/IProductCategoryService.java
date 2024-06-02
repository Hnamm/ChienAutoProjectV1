package com.hainam.springbootchienauto.services;

import com.hainam.springbootchienauto.dtos.ProductCategoryDTO;
import com.hainam.springbootchienauto.entity.Product;
import com.hainam.springbootchienauto.entity.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductCategoryService {
    ProductCategory createProductCategory(ProductCategoryDTO productCategoryDTO);
    ProductCategory getProductCategoryById(long id);
    List<ProductCategory> getAllProductCategory();
    ProductCategory updateProductCategory(long id, ProductCategoryDTO productCategoryDTO);
    void deleteProductCategory(long id);

    List<Product> getProductsByCategoryId(long categoryId);
}
