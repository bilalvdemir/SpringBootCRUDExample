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
@Tag(name = "Employee Management", description = "APIs for managing employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Operation(
        summary = "Get all employees",
        description = "Retrieves paginated list of all employees"
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employees")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {

        log.info("Fetching employees with pagination: {}", pageable);

        var employees = employeeService.getAllEmployees(pageable);
        var response = employees.map(employeeMapper::toResponse);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get employee by username")
    @ApiResponse(responseCode = "200", description = "Employee found")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @GetMapping("/{username}")
    public ResponseEntity<EmployeeResponse> getEmployeeByUsername(
            @Parameter(description = "Username of the employee")
            @PathVariable String username) {

        log.info("Fetching employee: {}", username);

        var employee = employeeService.getEmployeeByUsername(username);
        var response = employeeMapper.toResponse(employee);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create new employee")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "409", description = "Employee already exists")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {

        log.info("Creating employee: {}", request.getUsername());

        var employee = employeeMapper.toEntity(request);
        var createdEmployee = employeeService.createEmployee(employee);
        var response = employeeMapper.toResponse(createdEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update employee")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable String id,
            @Valid @RequestBody UpdateEmployeeRequest request) {

        log.info("Updating employee: {}", id);

        var updatedEmployee = employeeService.updateEmployee(id, request);
        var response = employeeMapper.toResponse(updatedEmployee);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete employee")
    @ApiResponse(responseCode = "204", description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @ApiResponse(responseCode = "429", description = "Too many requests - Rate limit: 10 requests per second per IP")
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String username) {

        log.info("Deleting employee: {}", username);

        employeeService.deleteEmployee(username);

        return ResponseEntity.noContent().build();
    }
}
