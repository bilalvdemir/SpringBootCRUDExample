package com.bilald.crudsample.controller;

import com.bilald.crudsample.dto.request.SalaryInfoRequest;
import com.bilald.crudsample.dto.response.SalaryInfoResponse;
import com.bilald.crudsample.mapper.SalaryMapper;
import com.bilald.crudsample.model.GetSalaryInfo;
import com.bilald.crudsample.model.SalaryInfo;
import com.bilald.crudsample.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/salary")
@Slf4j
public class SalaryController {

    private final SalaryMapper salaryMapper = Mappers.getMapper(SalaryMapper.class);
    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping("/")
    public ResponseEntity<SalaryInfoResponse> getSalaryInfo(@RequestBody SalaryInfoRequest salaryInfoRequest) {
        log.info("[getSalaryInfo] - get salary info requested for: {}", salaryInfoRequest.getUsername());

        GetSalaryInfo getSalaryInfo = salaryMapper.toGetSalaryInfo(salaryInfoRequest);
        SalaryInfo result = salaryService.getSalaryInfo(getSalaryInfo);
        SalaryInfoResponse response = salaryMapper.toSalaryInfoResponse(result);

        log.info("[getSalaryInfo] - get salary info request completed successfully for: {}", salaryInfoRequest.getUsername());
        return ResponseEntity.ok(response);
    }
}
