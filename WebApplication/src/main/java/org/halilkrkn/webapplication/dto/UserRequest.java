package org.halilkrkn.webapplication.dto;

public record UserRequest(
        String name,
        String email,
        String phone
) {}
