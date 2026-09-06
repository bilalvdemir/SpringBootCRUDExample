package com.bilald.crudsample.controller;

import com.bilald.crudsample.dto.request.CreateRoleBaseSalaryRequest;
import com.bilald.crudsample.dto.request.UpdateRoleBaseSalaryRequest;
import com.bilald.crudsample.dto.response.RoleBaseSalaryResponse;
import com.bilald.crudsample.mapper.RoleBaseSalaryMapper;
import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.service.RoleBaseSalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role-base-salaries")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Role Base Salary Management", description = "APIs for managing base salaries by employee role")
public class RoleBaseSalaryController {

    private final RoleBaseSalaryService roleBaseSalaryService;
    private final RoleBaseSalaryMapper roleBaseSalaryMapper;

    @Operation(
        summary = "Get all role base salaries",
        description = "Retrieves list of all role base salaries"
    )
    @ApiResponse(responseCode = "200", description = "Successfully retrieved role base salaries")
    @GetMapping
    public ResponseEntity<List<RoleBaseSalaryResponse>> getAll() {
        log.info("Fetching all role base salaries");

        var roleBaseSalaries = roleBaseSalaryService.getAll();
        var response = roleBaseSalaryMapper.toResponseList(roleBaseSalaries);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get role base salary by role")
    @ApiResponse(responseCode = "200", description = "Role base salary found")
    @ApiResponse(responseCode = "404", description = "Role base salary not found")
    @GetMapping("/{role}")
    public ResponseEntity<RoleBaseSalaryResponse> getByRole(
            @Parameter(description = "Employee role")
            @PathVariable EmployeeRole role) {

        log.info("Fetching role base salary for role: {}", role);

        var roleBaseSalary = roleBaseSalaryService.getByRole(role);
        var response = roleBaseSalaryMapper.toResponse(roleBaseSalary);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create new role base salary")
    @ApiResponse(responseCode = "201", description = "Role base salary created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "409", description = "Role base salary already exists for this role")
    @PostMapping
    public ResponseEntity<RoleBaseSalaryResponse> create(
            @Valid @RequestBody CreateRoleBaseSalaryRequest request) {

        log.info("Creating role base salary for role: {}", request.getRole());

        var roleBaseSalary = roleBaseSalaryMapper.toEntity(request);
        var createdRoleBaseSalary = roleBaseSalaryService.create(roleBaseSalary);
        var response = roleBaseSalaryMapper.toResponse(createdRoleBaseSalary);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update role base salary")
    @ApiResponse(responseCode = "200", description = "Role base salary updated successfully")
    @ApiResponse(responseCode = "404", description = "Role base salary not found")
    @PutMapping("/{id}")
    public ResponseEntity<RoleBaseSalaryResponse> update(
            @PathVariable String id,
            @Valid @RequestBody UpdateRoleBaseSalaryRequest request) {

        log.info("Updating role base salary: {}", id);

        var updatedRoleBaseSalary = roleBaseSalaryService.update(id, request);
        var response = roleBaseSalaryMapper.toResponse(updatedRoleBaseSalary);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete role base salary")
    @ApiResponse(responseCode = "204", description = "Role base salary deleted successfully")
    @ApiResponse(responseCode = "404", description = "Role base salary not found")
    @DeleteMapping("/{role}")
    public ResponseEntity<Void> delete(@PathVariable EmployeeRole role) {

        log.info("Deleting role base salary for role: {}", role);

        roleBaseSalaryService.delete(role);

        return ResponseEntity.noContent().build();
    }
}
