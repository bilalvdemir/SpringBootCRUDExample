package com.bilald.crudsample.mapper;

import com.bilald.crudsample.dto.request.CreateRoleBaseSalaryRequest;
import com.bilald.crudsample.dto.request.UpdateRoleBaseSalaryRequest;
import com.bilald.crudsample.dto.response.RoleBaseSalaryResponse;
import com.bilald.crudsample.model.RoleBaseSalary;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RoleBaseSalaryMapper {

    RoleBaseSalaryResponse toResponse(RoleBaseSalary roleBaseSalary);

    List<RoleBaseSalaryResponse> toResponseList(List<RoleBaseSalary> roleBaseSalaries);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    RoleBaseSalary toEntity(CreateRoleBaseSalaryRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntity(UpdateRoleBaseSalaryRequest request, @MappingTarget RoleBaseSalary roleBaseSalary);
}
