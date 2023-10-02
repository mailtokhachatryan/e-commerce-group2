package com.smartcode.ecommerce.util.constants;

public enum RoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String name;

    RoleEnum(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
