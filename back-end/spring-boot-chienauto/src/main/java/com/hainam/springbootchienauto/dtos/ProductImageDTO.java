package com.hainam.springbootchienauto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hainam.springbootchienauto.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {


    @JsonProperty( "product_id")
    private Long productId;

    @JsonProperty("image_url")
    private String imageUrl;


}
