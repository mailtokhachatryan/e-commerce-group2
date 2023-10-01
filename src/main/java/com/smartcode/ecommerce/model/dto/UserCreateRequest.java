package com.smartcode.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserCreateRequest extends BaseUserDto {

    @NotBlank
//    @Pattern(regexp="[\w_\\.]+")
    private String password;
}
