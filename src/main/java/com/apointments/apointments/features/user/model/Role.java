package com.apointments.apointments.features.user.model;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin"),
    CLIENT("client"),
    EMPLOYEE("employee");

    private final String role;

    Role(String role){
        this.role = role;
    }
}
