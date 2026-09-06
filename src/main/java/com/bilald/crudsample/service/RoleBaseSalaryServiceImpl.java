package com.bilald.crudsample.service;

import com.bilald.crudsample.dto.request.UpdateRoleBaseSalaryRequest;
import com.bilald.crudsample.exception.RoleBaseSalaryAlreadyExistsException;
import com.bilald.crudsample.exception.RoleBaseSalaryNotFoundException;
import com.bilald.crudsample.mapper.RoleBaseSalaryMapper;
import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.RoleBaseSalary;
import com.bilald.crudsample.repository.RoleBaseSalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleBaseSalaryServiceImpl implements RoleBaseSalaryService {

    private final RoleBaseSalaryMapper roleBaseSalaryMapper = Mappers.getMapper(RoleBaseSalaryMapper.class);
    private final RoleBaseSalaryRepository roleBaseSalaryRepository;

    @Override
    @Cacheable(value = "roleBaseSalaries", key = "#role", unless = "#result == null")
    public RoleBaseSalary getByRole(EmployeeRole role) {
        log.debug("Fetching role base salary for role: {}", role);
        return roleBaseSalaryRepository.findByRole(role)
                .orElseThrow(() -> new RoleBaseSalaryNotFoundException(role));
    }

    @Override
    @Cacheable(value = "roleBaseSalaries", key = "#id", unless = "#result == null")
    public RoleBaseSalary getById(String id) {
        log.debug("Fetching role base salary by id: {}", id);
        return roleBaseSalaryRepository.findById(id)
                .orElseThrow(() -> new RoleBaseSalaryNotFoundException(id));
    }

    @Override
    @Cacheable(value = "roleBaseSalaries", key = "'all'")
    public List<RoleBaseSalary> getAll() {
        log.debug("Fetching all role base salaries");
        return roleBaseSalaryRepository.findAll();
    }

    @Override
    @Cacheable(value = "roleBaseSalaries", key = "'salary:' + #role", unless = "#result == null")
    public double getBaseSalaryByRole(EmployeeRole role) {
        log.debug("Fetching base salary amount for role: {}", role);
        RoleBaseSalary roleBaseSalary = roleBaseSalaryRepository.findByRole(role)
                .orElseThrow(() -> new RoleBaseSalaryNotFoundException(role));
        return roleBaseSalary.getBaseSalary();
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "roleBaseSalaries", allEntries = true)
    })
    public RoleBaseSalary create(RoleBaseSalary roleBaseSalary) {
        log.debug("Creating role base salary for role: {}", roleBaseSalary.getRole());

        if (roleBaseSalaryRepository.existsByRole(roleBaseSalary.getRole())) {
            throw new RoleBaseSalaryAlreadyExistsException(roleBaseSalary.getRole());
        }

        return roleBaseSalaryRepository.save(roleBaseSalary);
    }

    @Override
    @Transactional
    @Caching(
            put = @CachePut(value = "roleBaseSalaries", key = "#result.role"),
            evict = @CacheEvict(value = "roleBaseSalaries", allEntries = true)
    )
    public RoleBaseSalary update(String id, UpdateRoleBaseSalaryRequest request) {
        log.info("Updating role base salary: {}", id);

        RoleBaseSalary roleBaseSalary = roleBaseSalaryRepository.findById(id)
                .orElseThrow(() -> new RoleBaseSalaryNotFoundException(id));

        roleBaseSalaryMapper.updateEntity(request, roleBaseSalary);

        return roleBaseSalaryRepository.save(roleBaseSalary);
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "roleBaseSalaries", key = "#role"),
                    @CacheEvict(value = "roleBaseSalaries", allEntries = true)
            }
    )
    public void delete(EmployeeRole role) {
        log.info("Deleting role base salary for role: {}", role);

        RoleBaseSalary roleBaseSalary = roleBaseSalaryRepository.findByRole(role)
                .orElseThrow(() -> new RoleBaseSalaryNotFoundException(role));

        roleBaseSalaryRepository.delete(roleBaseSalary);
    }
}
