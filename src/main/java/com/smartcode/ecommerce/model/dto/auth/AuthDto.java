package com.smartcode.ecommerce.model.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDto {

    private String accessToken;

    public AuthDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
