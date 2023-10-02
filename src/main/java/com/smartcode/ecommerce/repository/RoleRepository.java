package com.smartcode.ecommerce.repository;

import com.smartcode.ecommerce.model.entity.RoleEntity;
import com.smartcode.ecommerce.util.constants.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findByRole(RoleEnum roleEnum);

    boolean existsByRole(RoleEnum roleEnum);
}
