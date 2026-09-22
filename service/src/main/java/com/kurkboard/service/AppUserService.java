package com.kurkboard.service;

import com.kurkboard.entity.AppUserEntity;
import com.kurkboard.UserRole;
import com.kurkboard.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUserEntity createUser(
            String username,
            String password,
            UserRole role
    ) {
        if (appUserRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        AppUserEntity user = new AppUserEntity(
                username,
                passwordEncoder.encode(password),
                role
        );

        return appUserRepository.save(user);
    }

    public AppUserEntity createAdmin(String username, String password) {
        return createUser(username, password, UserRole.ADMIN);
    }
}