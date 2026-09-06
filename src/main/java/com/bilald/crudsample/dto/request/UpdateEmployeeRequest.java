package com.bilald.crudsample.dto.request;

import com.bilald.crudsample.common.enums.EmployeeRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {

    @Email(message = "Email must be valid")
    private String email;

    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Size(max = 100, message = "Lastname cannot exceed 100 characters")
    private String lastname;

    @Size(max = 100, message = "Department cannot exceed 100 characters")
    private String department;

    private EmployeeRole role;

    // Note: Username and password updates require separate endpoints
}
