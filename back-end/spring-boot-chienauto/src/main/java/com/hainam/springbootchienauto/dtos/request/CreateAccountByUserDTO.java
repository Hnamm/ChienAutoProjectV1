package com.hainam.springbootchienauto.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateAccountByUserDTO {
    @JsonProperty("full_name")
    private String fullName;
    private String phone;
    private String password;
}
