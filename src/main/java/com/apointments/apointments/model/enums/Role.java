package com.apointments.apointments.model.enums;

public enum Role {
    ADMIN("admin"),
    CLIENT("client"),
    EMPLOYEE("employee");

    private final String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
