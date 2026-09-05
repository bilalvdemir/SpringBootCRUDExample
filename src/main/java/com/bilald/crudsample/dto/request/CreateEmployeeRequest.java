package com.bilald.crudsample.dto.request;

import com.bilald.crudsample.model.EmployeeRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9.,$;]+$",
             message = "Username must start with letter and contain only alphanumeric, ., $, ;")
    private String username;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=.]).{8,}$",
             message = "Password must contain uppercase, lowercase, digit and special character")
    private String password;

    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Size(max = 100, message = "Lastname cannot exceed 100 characters")
    private String lastname;

    @NotBlank(message = "Department is required")
    @Size(max = 100, message = "Department cannot exceed 100 characters")
    private String department;

    @NotNull(message = "Role is required")
    private EmployeeRole role;
}
