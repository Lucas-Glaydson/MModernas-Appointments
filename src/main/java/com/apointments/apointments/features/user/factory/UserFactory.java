package com.apointments.apointments.features.user.factory;

import com.apointments.apointments.features.user.model.Role;
import com.apointments.apointments.features.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {
    public UserModel create(UserModel model, String passwordHashed, Role role){
        model.setPasswordHashed(passwordHashed);
        model.setRole(role);

        return model;
    }
}
