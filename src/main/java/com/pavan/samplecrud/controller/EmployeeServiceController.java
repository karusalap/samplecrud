package com.pavan.samplecrud.controller;

import com.pavan.samplecrud.model.APIAudit;
import com.pavan.samplecrud.model.Employee;
import com.pavan.samplecrud.service.APIAuditService;
import com.pavan.samplecrud.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("employees")
public class EmployeeServiceController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private APIAuditService apiAuditService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceController.class);

    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Integer> createEmployee(@RequestBody @Validated Employee employee, HttpServletRequest request) {
        Integer employeeId = employeeService.saveEmployee(employee);
        logAudit(employee, request);
        logger.info("Employee created with ID: {}", employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateEmployee(@RequestBody @Validated Employee employee, @PathVariable @Validated @Min(1) @Max(1000) Integer id) {
        employee.setId(id);
        boolean updated = employeeService.updateEmployee(employee);
        logger.info("Update employee with ID: {} - Success: {}", id, updated);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @Validated @Min(1) @Max(1000) Integer id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        logger.info("Delete employee with ID: {} - Success: {}", id, isDeleted);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(() -> {
            logger.warn("Employee not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        });
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Employee> getEmployeeByCode(@PathVariable String code) {
        Optional<Employee> employee = employeeService.findEmployeeByCode(code);
        return employee.map(ResponseEntity::ok).orElseGet(() -> {
            logger.warn("Employee not found with code: {}", code);
            return ResponseEntity.notFound().build();
        });
    }

    private void logAudit(Employee employee, HttpServletRequest request) {
        APIAudit apiAudit = new APIAudit();
        apiAudit.setRequestReceivedOn(LocalDateTime.now());
        apiAudit.setRequestBody(employee.toString());
        apiAudit.setClientIp(request.getLocalAddr());
        apiAuditService.createAudit(apiAudit);
        logger.info("Audit logged for employee creation: {}", employee);
    }
}
