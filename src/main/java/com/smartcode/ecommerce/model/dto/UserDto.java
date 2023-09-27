package com.smartcode.ecommerce.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class UserDto extends BaseUserDto {
    private Integer id;
    private BigDecimal balance;
}
