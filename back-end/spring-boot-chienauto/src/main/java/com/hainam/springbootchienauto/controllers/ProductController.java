package com.hainam.springbootchienauto.controllers;

import com.hainam.springbootchienauto.dtos.ProductDTO;
import com.hainam.springbootchienauto.dtos.ProductImageDTO;
import com.hainam.springbootchienauto.entity.Product;
import com.hainam.springbootchienauto.entity.ProductImage;
import com.hainam.springbootchienauto.exceptions.DataNotFoundException;
import com.hainam.springbootchienauto.exceptions.InvalidParamException;
import com.hainam.springbootchienauto.services.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService iProductService;
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
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        List<Product> products = iProductService.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(convertToDto(product));
        }
        return ResponseEntity.ok(productDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long productId) throws Exception {
        Product product = iProductService.getProductById(productId);
        ProductDTO productDTO = convertToDto(product);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable String name){
        List<Product> products= iProductService.getProductByName(name);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products) {
            productDTOList.add(convertToDto(product));
        }
        return ResponseEntity.ok(productDTOList);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(
            @Valid @RequestBody ProductDTO productDTO,
            BindingResult result
            ){
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(String.join(", ", errorMessages));
            }
            iProductService.createProduct(productDTO);
            return ResponseEntity.ok(productDTO);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping(value = "uploads/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @PathVariable("id") Long productId,
            @ModelAttribute("files") List<MultipartFile> files

    ) {

        try {
            Product existingProduct = iProductService.getProductById(productId);
            files = files == null ? new ArrayList<MultipartFile>() : files;
            if(files.size() > ProductImage.MAX_IMAGES_PER_PRODUCT){
                return ResponseEntity.badRequest().body("Khong duoc qua 5 anh");
            }
            List<ProductImage> productImageList = new ArrayList<>();
            for (MultipartFile file : files) {
                if (file.getSize() == 0) {
                    continue;
                }
                if (file.getSize() > 10 * 1024 * 1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("file qua lon");
                }
                String contentType = file.getContentType();
                if (contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("file khong dung dinh dang");
                }
                String filename = storeFile(file);
                ProductImage productImage = iProductService.createProductImage(
                        existingProduct.getId()
                        , ProductImageDTO
                                .builder()
                                .imageUrl(filename)
                                .build());
                productImageList.add(productImage);
            }
            List<ProductImageDTO> productImageDTOList = productImageList.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

            return ResponseEntity.ok().body(productImageDTOList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ProductImageDTO convertToDto(ProductImage productImage) {
        return ProductImageDTO.builder()
                .productId(productImage.getProduct().getId())
                .imageUrl(productImage.getImageUrl())
                .build();
    }

    private String storeFile(MultipartFile file) throws IOException{
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        String newFile = UUID.randomUUID().toString() + "_" +filename;
        Path uploadDir = Paths.get("uploads");
        if (!Files.exists(uploadDir)){
            Files.createDirectory(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(), newFile);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return  newFile;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String productId){
        return ResponseEntity.ok("xoa thanh cong san pham co id" + productId);
    }
}
