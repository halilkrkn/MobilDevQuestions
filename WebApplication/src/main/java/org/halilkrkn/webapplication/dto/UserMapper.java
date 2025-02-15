package org.halilkrkn.webapplication.dto;

import org.halilkrkn.webapplication.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public User toUserRequest(UserRequest webUser) {
        return User.builder()
                .name(webUser.name())
                .email(webUser.email())
                .phone(webUser.phone())
                .build();
    }

    public UserResponse fromUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone()
        );
    }
}
