package com.catalogapp.securityservicejwt.domain.enums;

public enum RoleEnum {

    SITE_USER("ROLE_SITE_USER"),
    EMPLOYEE("ROLE_EMPLOYEE");

    private final String description;

    RoleEnum(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
