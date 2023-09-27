package com.smartcode.ecommerce.service;

import com.smartcode.ecommerce.model.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserDto create(UserCreateRequest request);

    List<UserDto> getAll();

    UserDto getById(Integer id);

    UserDto update(Integer id, UserEntity userEntity);

    void delete(Integer id);
}
