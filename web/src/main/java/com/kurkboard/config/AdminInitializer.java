package com.kurkboard.config;

import com.kurkboard.repository.AppUserRepository;
import com.kurkboard.service.AppUserService;
import com.kurkboard.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

    @Value("${SUPER_ADMIN_USERNAME}")
    private String username;

    @Value("${SUPER_ADMIN_PASSWORD}")
    private String password;

    public AdminInitializer(
            AppUserRepository appUserRepository,
            AppUserService appUserService
    ) {
        this.appUserRepository = appUserRepository;
        this.appUserService = appUserService;
    }

    @Override
    public void run(String... args) {
        if (appUserRepository.findByUsername(username).isEmpty()) {
            appUserService.createUser(
                    username,
                    password,
                    UserRole.SUPER_ADMIN
            );
        }
    }
}