package org.halilkrkn.webapplication.service;

import org.halilkrkn.webapplication.dto.UserRequest;
import org.halilkrkn.webapplication.dto.UserResponse;


import java.util.List;

public interface UserService {
    Integer createUser(UserRequest webUser);

    List<UserResponse> getAllUsers();

    void deleteUser(Integer id);
}
