package org.halilkrkn.webapplication.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.halilkrkn.webapplication.dto.UserRequest;
import org.halilkrkn.webapplication.dto.UserMapper;
import org.halilkrkn.webapplication.dto.UserResponse;
import org.halilkrkn.webapplication.entity.User;
import org.halilkrkn.webapplication.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Integer createUser(UserRequest webUser) {
        val user = userMapper.toUserRequest(webUser);
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
