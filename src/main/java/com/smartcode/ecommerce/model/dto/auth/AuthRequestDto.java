package com.smartcode.ecommerce.model.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDto {
    private String username;
    private String password;
}
