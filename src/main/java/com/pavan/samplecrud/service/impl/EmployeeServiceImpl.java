package com.pavan.samplecrud.service.impl;

import com.pavan.samplecrud.model.Employee;
import com.pavan.samplecrud.repository.EmployeeRepository;
import com.pavan.samplecrud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Integer saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee.getId();
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            employeeRepository.save(employee);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEmployee(Integer Id) {
        if (employeeRepository.existsById(Id)) {
            employeeRepository.deleteById(Id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Employee> findEmployeeById(Integer Id) {
        return employeeRepository.findById(Id);
    }

    @Override
    public Optional<Employee> findEmployeeByCode(String code) {
        return employeeRepository.findByCode(code);
    }

}
