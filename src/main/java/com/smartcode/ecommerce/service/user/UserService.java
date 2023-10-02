package com.smartcode.ecommerce.service.user;

import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.dto.filter.UserFilterModel;
import com.smartcode.ecommerce.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserDto> getAll(UserFilterModel userFilterModel);

    UserDto getById(Integer id);

    UserDto update(Integer id, UserEntity userEntity);

    void delete(Integer id);
}
