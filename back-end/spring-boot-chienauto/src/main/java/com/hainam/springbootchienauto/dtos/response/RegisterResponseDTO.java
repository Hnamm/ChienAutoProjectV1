package com.hainam.springbootchienauto.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponseDTO {
    private String phone;
    private String password;
}
