package com.hainam.springbootchienauto.services;

import com.hainam.springbootchienauto.dtos.ProductCategoryDTO;
import com.hainam.springbootchienauto.entity.Product;
import com.hainam.springbootchienauto.entity.ProductCategory;
import com.hainam.springbootchienauto.repositories.ProductCategoryRepository;
import com.hainam.springbootchienauto.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryService implements IProductCategoryService{

    private final ProductCategoryRepository productCategoryRepository;

    private  final ProductRepository productRepository;


//    public ProductCategoryService(ProductCategoryRepository productCategoryRepository){
//        this.productCategoryRepository = productCategoryRepository;
//    }
    @Override
    public ProductCategory createProductCategory(ProductCategoryDTO productCategoryDTO) {
        ProductCategory newProductCategory = ProductCategory
                .builder()
                .categoryName(productCategoryDTO.getCategoryName())
                .build();

        return productCategoryRepository.save(newProductCategory);
    }

    @Override
    public ProductCategory getProductCategoryById(long id) {
        return productCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Category not found"));
    }

    @Override
    public List<ProductCategory> getAllProductCategory() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory updateProductCategory(long id, ProductCategoryDTO productCategoryDTO) {
        ProductCategory existingProductCategory = getProductCategoryById(id);
        existingProductCategory.setCategoryName(productCategoryDTO.getCategoryName());
        return existingProductCategory;
    }

    @Override
    public void deleteProductCategory(long id) {
        productCategoryRepository.deleteById(id);
    }

    public List<Product> getProductsByCategoryId(long categoryId) {
        ProductCategory category = productCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return productRepository.findByProductCategoryId(categoryId);
    }
}
