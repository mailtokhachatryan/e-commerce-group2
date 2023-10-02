package com.smartcode.ecommerce.service.user.impl;

import com.smartcode.ecommerce.exceptions.ResourceNotFoundException;
import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.dto.filter.UserFilterModel;
import com.smartcode.ecommerce.model.entity.UserEntity;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.user.UserService;
import com.smartcode.ecommerce.spec.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserSpecification userSpecification;




    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll(UserFilterModel userFilterModel) {
        return userRepository.findAll(userSpecification.filter(userFilterModel)).stream().map(userMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(String.format("User by id: %d not found", id));
                    return new ResourceNotFoundException(String.format("User by id: %d not found", id));
                });
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional
    public UserDto update(Integer id, UserEntity requestModel) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User by id: %d not found", id)));

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
