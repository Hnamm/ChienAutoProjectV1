package com.hainam.springbootchienauto.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("phone_number")
    @NotBlank(message = "so dien thoai khong duoc de trong")
    private String phoneNumber;

    @NotBlank(message = "mat khau khong duoc de trong")
    private String password;

    @JsonProperty("retype_password")
    private String retypePassword;

    @NotNull(message = "role_id phai co")
    @JsonProperty("role_id")
    private long roleId;
}
