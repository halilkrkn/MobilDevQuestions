package org.halilkrkn.webapplication.dto;

public record UserResponse(
        Integer id,
        String name,
        String email,
        String phone
) {}
