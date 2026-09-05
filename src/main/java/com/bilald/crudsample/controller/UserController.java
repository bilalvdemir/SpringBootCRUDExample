package com.bilald.crudsample.controller;

import com.bilald.crudsample.dto.request.CreateUserRequest;
import com.bilald.crudsample.dto.request.UpdateUserRequest;
import com.bilald.crudsample.dto.response.UserResponse;
import com.bilald.crudsample.mapper.UserMapper;
import com.bilald.crudsample.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(
        summary = "Get all users",
        description = "Retrieves paginated list of all users"
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved users")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {

        log.info("Fetching users with pagination: {}", pageable);

        var users = userService.getAllUsers(pageable);
        var response = users.map(userMapper::toResponse);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by username")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(
            @Parameter(description = "Username of the user")
            @PathVariable String username) {

        log.info("Fetching user: {}", username);

        var user = userService.getUserByUsername(username);
        var response = userMapper.toResponse(user);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "User created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "409", description = "User already exists")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request) {

        log.info("Creating user: {}", request.getUsername());

        var user = userMapper.toEntity(request);
        var createdUser = userService.createUser(user);
        var response = userMapper.toResponse(createdUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update user")
    @ApiResponse(responseCode = "200", description = "User updated successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String id,
            @Valid @RequestBody UpdateUserRequest request) {

        log.info("Updating user: {}", id);

        var updatedUser = userService.updateUser(id, request);
        var response = userMapper.toResponse(updatedUser);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {

        log.info("Deleting user: {}", username);

        userService.deleteUser(username);

        return ResponseEntity.noContent().build();
    }
}
