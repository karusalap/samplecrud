package com.pavan.samplecrud.service;

import com.pavan.samplecrud.entity.Employee;

import java.util.Optional;

public interface EmployeeService {
    Integer saveEmployee(Employee employee);

    boolean updateEmployee(Employee employee);

    boolean deleteEmployee(Integer Id);

    Optional<Employee> findEmployeeById(Integer Id);

    Optional<Employee> findEmployeeByCode(String code);
}
