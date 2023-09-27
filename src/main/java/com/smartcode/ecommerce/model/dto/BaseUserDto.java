package com.smartcode.ecommerce.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class BaseUserDto {

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastName;
    private String middleName;
    @NotBlank
    @Email
    private String email;
    @NotNull
    private Integer age;


}
