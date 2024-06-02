package com.hainam.springbootchienauto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.hainam.springbootchienauto.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Khong duoc de trong")
    @Size(min = 3, max= 255, message = "sku phai co do dai tu 3 den 255")
    private String sku;
    @NotBlank(message = "Khong duoc de trong")
    @Size(min = 3, max= 255, message = "name phai co do dai tu 3 den 255")
    private String name;
    private String description;
    @Min(value = 0, message = "gia ")
    @JsonProperty("unit_price")
    private BigDecimal unitPrice;
    @JsonProperty("image_url")
    private String imageUrl;
    private boolean active;
    @JsonProperty("units_in_stock")
    private int unitsInStock;
    @JsonProperty("max_speed")
    private int maxSpeed;
    @JsonProperty("road_one_time")
    private int roadOneTime;
    @JsonProperty("battery_time")
    private int batteryTime;
    @JsonProperty("category_id")
    private long category_id;


}
