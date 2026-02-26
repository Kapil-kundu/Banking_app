package com.kapil.digitalbank.Banking.App.Repositories;

import com.kapil.digitalbank.Banking.App.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByEmail(String email);
}
