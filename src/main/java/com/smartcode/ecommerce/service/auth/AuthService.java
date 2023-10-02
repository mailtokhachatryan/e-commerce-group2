package com.smartcode.ecommerce.service.auth;

import com.smartcode.ecommerce.model.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.dto.auth.AuthDto;
import com.smartcode.ecommerce.model.dto.auth.AuthRequestDto;

public interface AuthService {
    UserDto register(UserCreateRequest request);

    AuthDto login(AuthRequestDto request);
}
