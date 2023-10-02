package com.smartcode.ecommerce.config;

import com.smartcode.ecommerce.model.entity.RoleEntity;
import com.smartcode.ecommerce.model.entity.UserEntity;
import com.smartcode.ecommerce.repository.RoleRepository;
import com.smartcode.ecommerce.repository.UserRepository;
import com.smartcode.ecommerce.util.constants.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class InitConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        createRoles();
        createAdmins();
    }

    private void createAdmins() {

        if (!userRepository.existsByEmail("admin@gmail.com")) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName("Admin");
            userEntity.setLastName("Admin");
            userEntity.setAge(50);
            userEntity.setBalance(new BigDecimal(0));
            userEntity.setEmail("admin@gmail.com");
            userEntity.setPassword(passwordEncoder.encode("password"));
            userEntity.setCode("");
            userEntity.setVerified(true);
            userEntity.setRole(roleRepository.findByRole(RoleEnum.ADMIN));
            userRepository.save(userEntity);
        }
    }

    private void createRoles() {
        if (!roleRepository.existsByRole(RoleEnum.ADMIN)) {
            RoleEntity admin = new RoleEntity();
            admin.setRole(RoleEnum.ADMIN);
            roleRepository.save(admin);
        }

        if (!roleRepository.existsByRole(RoleEnum.USER)) {
            RoleEntity user = new RoleEntity();
            user.setRole(RoleEnum.USER);
            roleRepository.save(user);
        }
    }

}
