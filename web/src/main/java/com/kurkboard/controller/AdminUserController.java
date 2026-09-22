package com.kurkboard.controller;

import com.kurkboard.dto.AdminResponse;
import com.kurkboard.dto.CreateAdminRequest;
import com.kurkboard.entity.AppUserEntity;
import com.kurkboard.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AppUserService appUserService;

    public AdminUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<AdminResponse> createAdmin(
            @RequestBody CreateAdminRequest request
    ) {
        AppUserEntity user = appUserService.createAdmin(
                request.getUsername(),
                request.getPassword()
        );

        AdminResponse response = new AdminResponse(
                user.getId(),
                user.getUsername(),
                user.getRole().name()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}