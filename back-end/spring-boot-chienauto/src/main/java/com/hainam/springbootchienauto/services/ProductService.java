package com.hainam.springbootchienauto.services;

import com.hainam.springbootchienauto.dtos.ProductDTO;
import com.hainam.springbootchienauto.dtos.ProductImageDTO;
import com.hainam.springbootchienauto.entity.Product;
import com.hainam.springbootchienauto.entity.ProductCategory;
import com.hainam.springbootchienauto.entity.ProductImage;
import com.hainam.springbootchienauto.exceptions.DataNotFoundException;
import com.hainam.springbootchienauto.exceptions.InvalidParamException;
import com.hainam.springbootchienauto.repositories.ProductCategoryRepository;
import com.hainam.springbootchienauto.repositories.ProductImageRepository;
import com.hainam.springbootchienauto.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException{
        ProductCategory existingCategory = productCategoryRepository.findById(productDTO.getCategory_id()).
                orElseThrow(() -> new DataNotFoundException("Khong tim thay danh muc co id"+ productDTO.getCategory_id()));

        Product newProduct = Product.builder()
                .sku(productDTO.getSku())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .unitPrice(productDTO.getUnitPrice())
                .imageUrl(productDTO.getImageUrl())
                .unitsInStock(productDTO.getUnitsInStock())
                .maxSpeed(productDTO.getMaxSpeed())
                .roadOneTime(productDTO.getRoadOneTime())
                .batteryTime(productDTO.getBatteryTime())
                .productCategory(existingCategory)
                .build();

        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(Long id) throws DataNotFoundException {
        return productRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException("Khong tim thay san pham co id" + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws DataNotFoundException {
        Product existingProduct = getProductById(id);
        if(existingProduct != null){
            ProductCategory existingCategory = productCategoryRepository.findById(productDTO.getCategory_id()).
                    orElseThrow(() -> new DataNotFoundException("Khong tim thay danh muc co id"+ productDTO.getCategory_id()));
            existingProduct.setName(productDTO.getName());
            existingProduct.setSku(productDTO.getSku());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setUnitPrice(productDTO.getUnitPrice());
            existingProduct.setImageUrl(productDTO.getImageUrl());
            existingProduct.setUnitsInStock(productDTO.getUnitsInStock());
            existingProduct.setMaxSpeed(productDTO.getMaxSpeed());
            existingProduct.setRoadOneTime(productDTO.getRoadOneTime());
            existingProduct.setBatteryTime(productDTO.getBatteryTime());
            existingProduct.setProductCategory(existingCategory);
            return productRepository.save(existingProduct);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        optionalProduct.ifPresent(productRepository::delete);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public List<Product> findByProductCategoryId(Long id) {
        return productRepository.findByProductCategoryId(id);
    }

    @Override
    public ProductImage createProductImage( Long productId,
                                            ProductImageDTO productImageDTO) throws Exception {
        Product existingProduct = productRepository
                .findById(productId)
                .orElseThrow(() -> new DataNotFoundException("khong the tim thay san pham"));
        ProductImage newProductImage = ProductImage
                .builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        int size = productImageRepository.findByProductId(productId).size();
        if(size > ProductImage.MAX_IMAGES_PER_PRODUCT){
            throw new InvalidParamException("so anh phai nho hon " + ProductImage.MAX_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(newProductImage);
    }
}
