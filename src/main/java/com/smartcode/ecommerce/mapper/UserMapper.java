package com.smartcode.ecommerce.mapper;

import com.smartcode.ecommerce.model.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "firstname", source = "name")
    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "name", source = "firstname")
    UserEntity toEntity(UserCreateRequest request);
}
