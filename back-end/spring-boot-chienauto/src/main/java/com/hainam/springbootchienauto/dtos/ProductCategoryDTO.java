package com.hainam.springbootchienauto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hainam.springbootchienauto.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDTO {

    private Long id;

    @NotEmpty(message = "Category name not empty")
    @JsonProperty("category_name")
    private String categoryName;


}
