package org.halilkrkn.webapplication.controller;

import lombok.RequiredArgsConstructor;
import org.halilkrkn.webapplication.dto.UserRequest;
import org.halilkrkn.webapplication.dto.UserResponse;
import org.halilkrkn.webapplication.entity.User;
import org.halilkrkn.webapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Integer> createUser(
            @RequestBody UserRequest webUser
    ) {
        return ResponseEntity.ok(userService.createUser(webUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Integer id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
