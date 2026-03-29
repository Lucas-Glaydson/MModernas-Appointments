package com.apointments.apointments.features.user.factory;

import com.apointments.apointments.features.user.model.Role;
import com.apointments.apointments.features.user.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public UserModel create(UserModel model, String passwordHashed, Role role){
        model.setEmail(model.getEmail().toLowerCase().trim());
        model.setFirstName(model.getFirstName().toLowerCase().trim());
        model.setLastName(model.getLastName().toLowerCase().trim());
        model.setPasswordHashed(passwordHashed);
        model.setRole(role);

        return model;
    }
}
