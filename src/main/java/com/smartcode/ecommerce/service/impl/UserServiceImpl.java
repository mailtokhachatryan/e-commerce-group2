package com.smartcode.ecommerce.service.impl;

import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.entity.UserEntity;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    @Transactional
    public UserDto create(UserCreateRequest request) {

        UserEntity entity = userMapper.toEntity(request);

        entity.setCode("456879");

        UserEntity saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("User by id: %d not found", id)));
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto update(Integer id, UserEntity requestModel) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("User by id: %d not found", id)));

        userEntity.setName(requestModel.getName());
        userEntity.setLastName(requestModel.getLastName());
        userEntity.setMiddleName(requestModel.getMiddleName());
        userEntity.setAge(requestModel.getAge());
        userEntity.setEmail(requestModel.getEmail());

        return userMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }


}
