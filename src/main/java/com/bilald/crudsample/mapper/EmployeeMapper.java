package com.bilald.crudsample.mapper;

import com.bilald.crudsample.dto.request.CreateEmployeeRequest;
import com.bilald.crudsample.dto.request.UpdateEmployeeRequest;
import com.bilald.crudsample.dto.response.EmployeeResponse;
import com.bilald.crudsample.model.Employee;
import com.bilald.crudsample.model.SalaryInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EmployeeMapper {

    EmployeeResponse toResponse(Employee employee);

    List<EmployeeResponse> toResponseList(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Employee toEntity(CreateEmployeeRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(UpdateEmployeeRequest request, @MappingTarget Employee employee);

    @Mapping(target = "salary", expression = "java(salary)")
    SalaryInfo toSalaryInfo(Employee employee, double salary);
}
