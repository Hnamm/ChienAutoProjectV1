package com.hainam.springbootchienauto.services;

import com.hainam.springbootchienauto.dtos.ProductDTO;
import com.hainam.springbootchienauto.dtos.ProductImageDTO;
import com.hainam.springbootchienauto.entity.Product;
import com.hainam.springbootchienauto.entity.ProductCategory;
import com.hainam.springbootchienauto.entity.ProductImage;
import com.hainam.springbootchienauto.exceptions.DataNotFoundException;
import com.hainam.springbootchienauto.exceptions.InvalidParamException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    public Product createProduct(ProductDTO productDTO) throws Exception;
    Product getProductById(Long id) throws Exception;
    List<Product> getAllProducts();
    Product updateProduct(Long id, ProductDTO productDTO) throws Exception;
    void deleteProduct(Long id);
    boolean existsByName(String name);

    List<Product> getProductByName(String name);

    List<Product> findByProductCategoryId(Long id);

    ProductImage createProductImage(Long productId, ProductImageDTO productImageDTO) throws Exception;
}
