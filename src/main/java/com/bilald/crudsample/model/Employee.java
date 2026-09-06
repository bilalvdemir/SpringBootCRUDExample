package com.bilald.crudsample.model;

import com.bilald.crudsample.common.enums.EmployeeRole;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Employee extends User {

    private static final long serialVersionUID = 2L;

    @Indexed
    private String department;

    @Indexed
    private EmployeeRole role;
}
