package com.hainam.springbootchienauto.repositories;

import com.hainam.springbootchienauto.entity.Product;
import com.hainam.springbootchienauto.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {


}
