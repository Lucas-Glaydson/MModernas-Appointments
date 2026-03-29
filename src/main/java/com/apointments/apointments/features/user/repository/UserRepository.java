package com.apointments.apointments.features.user.repository;

import com.apointments.apointments.features.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findOneByEmail(String email);
    UserModel findOneByCpf(String cpf);

}
