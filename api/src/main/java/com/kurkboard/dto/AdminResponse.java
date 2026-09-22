package com.kurkboard.dto;

public class AdminResponse {

    private Integer id;
    private String username;
    private String role;

    public AdminResponse() {
    }

    public AdminResponse(Integer id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}