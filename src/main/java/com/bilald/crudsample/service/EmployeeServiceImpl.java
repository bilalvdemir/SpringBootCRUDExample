package com.bilald.crudsample.service;

import com.bilald.crudsample.dto.request.UpdateEmployeeRequest;
import com.bilald.crudsample.exception.UserAlreadyExistsException;
import com.bilald.crudsample.exception.UserNotFoundException;
import com.bilald.crudsample.mapper.EmployeeMapper;
import com.bilald.crudsample.model.Employee;
import com.bilald.crudsample.monitoring.EmployeeMetrics;
import com.bilald.crudsample.repository.EmployeeRepository;
import com.bilald.crudsample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    private final EmployeeRepository employeeRepository;
    private final EmployeeMetrics employeeMetrics;
    private final UserRepository userRepository;

    @Override
    @Cacheable(value = "employees", key = "#username", unless = "#result == null")
    public Employee getEmployeeByUsername(String username) {
        log.debug("Fetching employee from database: {}", username);
        return employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    @Override
    @Cacheable(value = "employees", key = "#id", unless = "#result == null")
    public Employee getEmployeeById(String id) {
        log.debug("Fetching employee from database by id: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Cacheable(value = "employees", key = "'all:' + #pageable.pageNumber + ':' + #pageable.pageSize + ':' + #pageable.sort")
    public Page<Employee> getAllEmployees(Pageable pageable) {
        log.debug("Fetching all employees with pagination: {}", pageable);
        return employeeRepository.findAll(pageable);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "employees", allEntries = true)
    })
    public Employee createEmployee(Employee user) {
        log.debug("Fetching create employee: {}", user.getUsername());

        if (employeeRepository.existsByUsername(user.getUsername()) || userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        if (employeeRepository.existsByEmail(user.getEmail()) || userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("Email already registered: " + user.getEmail());
        }

        Employee savedEmployee = employeeRepository.save(user);
        employeeMetrics.incrementEmployeeCreated();

        return savedEmployee;
    }

    @Override
    @Transactional
    @Caching(
            put = @CachePut(value = "employees", key = "#result.username"),
            evict = @CacheEvict(value = "employees", allEntries = true)
    )
    public Employee updateEmployee(String id, UpdateEmployeeRequest request) {
        log.info("Updating employee: {}", id);
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        employeeMapper.updateEntity(request, employee);

        Employee updatedEmployee = employeeRepository.save(employee);
        employeeMetrics.incrementEmployeeUpdated();

        return updatedEmployee;
    }

    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(value = "employees", key = "#username"),
                    @CacheEvict(value = "employees", allEntries = true)
            }
    )
    public void deleteEmployee(String username) {
        log.info("Deleting employee: {}", username);

        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        employeeRepository.delete(employee);
        employeeMetrics.incrementEmployeeDeleted();
    }
}
