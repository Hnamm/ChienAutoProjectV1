package com.hainam.springbootchienauto.controllers;

import com.hainam.springbootchienauto.dtos.ProductCategoryDTO;
import com.hainam.springbootchienauto.dtos.ProductDTO;
import com.hainam.springbootchienauto.entity.Product;
import com.hainam.springbootchienauto.entity.ProductCategory;
import com.hainam.springbootchienauto.services.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/productCategory")
@RequiredArgsConstructor
public class ProductCategoryController {
    @Autowired
    private final ProductCategoryService productCategoryService;

    private ProductDTO convertToDto(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .active(product.isActive())
                .description(product.getDescription())
                .unitPrice(product.getUnitPrice())
                .imageUrl(product.getImageUrl())
                .unitsInStock(product.getUnitsInStock())
                .maxSpeed(product.getMaxSpeed())
                .roadOneTime(product.getRoadOneTime())
                .batteryTime(product.getBatteryTime())
                .category_id(product.getProductCategory().getId())
                .build();
    }

    private ProductCategoryDTO convertToDto(ProductCategory productCategory) {
        return ProductCategoryDTO.builder()
                .id(productCategory.getId())
                .categoryName(productCategory.getCategoryName())
                .build();
    }
    @PostMapping("")
    public ResponseEntity<String> insertCategory(
            @Valid @RequestBody ProductCategoryDTO productCategoryDTO,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(String.join(", ", errorMessages));
        }
        productCategoryService.createProductCategory(productCategoryDTO);
        return ResponseEntity.ok("them danh muc: " + productCategoryDTO);
    }
    @GetMapping("")
    public ResponseEntity<List<ProductCategoryDTO>> getAllCategories() {
        List<ProductCategory>  productCategoryList = productCategoryService.getAllProductCategory();
        List<ProductCategoryDTO> productCategoryDTOList = new ArrayList<>();
        for(ProductCategory productCategory : productCategoryList){
            productCategoryDTOList.add(convertToDto(productCategory));
        }
        return ResponseEntity.ok(productCategoryDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getCategoryById(@PathVariable Long id) {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        if (productCategory == null) {
            return ResponseEntity.notFound().build();
        }
        ProductCategoryDTO productCategoryDTO = convertToDto(productCategory);
        return ResponseEntity.ok(productCategoryDTO);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryId(@PathVariable long id) {
        List<Product> products = productCategoryService.getProductsByCategoryId(id);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : products){
            productDTOList.add(convertToDto(product));

        }
        return ResponseEntity.ok(productDTOList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable long id) {
        return ResponseEntity.ok("cap nhat danh muc id = " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id) {
        return ResponseEntity.ok("xoa danh muc id = " + id);
    }
}
