package com.bilald.crudsample.controller;

import com.bilald.crudsample.dto.request.CreateEmployeeRequest;
import com.bilald.crudsample.dto.request.UpdateEmployeeRequest;
import com.bilald.crudsample.dto.response.EmployeeResponse;
import com.bilald.crudsample.mapper.EmployeeMapper;
import com.bilald.crudsample.service.EmployeeService;
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
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Employee Management", description = "APIs for managing users")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Operation(
        summary = "Get all users",
        description = "Retrieves paginated list of all users"
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved users")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {

        log.info("Fetching users with pagination: {}", pageable);

        var users = employeeService.getAllEmployees(pageable);
        var response = users.map(employeeMapper::toResponse);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by username")
    @ApiResponse(responseCode = "200", description = "Employee found")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @GetMapping("/{username}")
    public ResponseEntity<EmployeeResponse> getEmployeeByUsername(
            @Parameter(description = "Username of the user")
            @PathVariable String username) {

        log.info("Fetching user: {}", username);

        var user = employeeService.getEmployeeByUsername(username);
        var response = employeeMapper.toResponse(user);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "409", description = "Employee already exists")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {

        log.info("Creating user: {}", request.getUsername());

        var user = employeeMapper.toEntity(request);
        var createdEmployee = employeeService.createEmployee(user);
        var response = employeeMapper.toResponse(createdEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update user")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody UpdateEmployeeRequest request) {

        log.info("Updating user: {}", id);

        var updatedEmployee = employeeService.updateEmployee(id, request);
        var response = employeeMapper.toResponse(updatedEmployee);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user")
    @ApiResponse(responseCode = "204", description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String username) {

        log.info("Deleting user: {}", username);

        employeeService.deleteEmployee(username);

        return ResponseEntity.noContent().build();
    }
}
