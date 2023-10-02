package com.smartcode.ecommerce.service.auth.impl;

import com.smartcode.ecommerce.config.security.jwt.JwtTokenProvider;
import com.smartcode.ecommerce.exceptions.UsernameAlreadyExistsException;
import com.smartcode.ecommerce.mapper.UserMapper;
import com.smartcode.ecommerce.model.dto.UserCreateRequest;
import com.smartcode.ecommerce.model.dto.UserDto;
import com.smartcode.ecommerce.model.dto.auth.AuthDto;
import com.smartcode.ecommerce.model.dto.auth.AuthRequestDto;
import com.smartcode.ecommerce.model.entity.UserEntity;
import com.smartcode.ecommerce.repository.RoleRepository;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.service.auth.AuthService;
import com.smartcode.ecommerce.service.mail.MailService;
import com.smartcode.ecommerce.util.MD5Encoder;
import com.smartcode.ecommerce.util.RandomGenerator;
import com.smartcode.ecommerce.util.constants.RoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final MailService mailService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;


    @Override
    @Transactional
    public UserDto register(UserCreateRequest request) {
        validateUserRegistration(request);
        UserEntity entity = userMapper.toEntity(request);
        entity.setPassword(MD5Encoder.encode(entity.getPassword()));
        String code = RandomGenerator.generateNumericString(6);
        entity.setCode(code);
        entity.setBalance(new BigDecimal(0));
        entity.setRole(roleRepository.findByRole(RoleEnum.USER));
        UserEntity saved = userRepository.save(entity);
        mailService.sendEmail(entity.getEmail(), "Verification", "Your verification code is: " + code);
        return userMapper.toDto(saved);
    }

    @Override
    public AuthDto login(AuthRequestDto request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserEntity user = userRepository.findByEmail(request.getUsername());
        Integer userId = user.getId();
        String accessToken = jwtTokenProvider.generateAccessToken(userId, user.getEmail(), user.getRole().getRole().getName());

        String token = accessToken.split("\\.")[2];
        System.out.println(token);

        return new AuthDto(accessToken);
    }

    private void validateUserRegistration(UserCreateRequest user) {
        UserEntity userEntity = userRepository.findByEmail(user.getEmail());
        if (userEntity != null) {
            throw new UsernameAlreadyExistsException(String.format("User by email: %s already exists", user.getEmail()));
        }
    }

}
