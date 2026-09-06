package com.bilald.crudsample.mapper;

import com.bilald.crudsample.dto.request.SalaryInfoRequest;
import com.bilald.crudsample.dto.response.SalaryInfoResponse;
import com.bilald.crudsample.model.GetSalaryInfo;
import com.bilald.crudsample.model.SalaryInfo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface SalaryMapper {

    GetSalaryInfo toGetSalaryInfo(SalaryInfoRequest salaryInfoRequest);

    SalaryInfoResponse toSalaryInfoResponse(SalaryInfo response);
}
