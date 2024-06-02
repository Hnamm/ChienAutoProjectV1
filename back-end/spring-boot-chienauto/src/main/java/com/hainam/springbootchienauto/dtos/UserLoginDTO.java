package com.hainam.springbootchienauto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    @JsonProperty("phone_number")
    @NotBlank(message = "so dien thoai khong duoc de trong")
    private String phoneNumber;

    @NotBlank(message = "mat khau khong duoc de trong")
    private String password;
}
