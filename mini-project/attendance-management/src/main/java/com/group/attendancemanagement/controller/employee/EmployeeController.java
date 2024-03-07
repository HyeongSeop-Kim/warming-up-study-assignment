package com.group.attendancemanagement.controller.employee;

import com.group.attendancemanagement.dto.employee.request.EmployeeCreateRequest;
import com.group.attendancemanagement.dto.employee.response.EmployeeResponse;
import com.group.attendancemanagement.service.employee.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/api/v1/employee")
    public void createEmployee(@Valid @RequestBody EmployeeCreateRequest request) {
        employeeService.createEmployee(request, LocalDate.now());
    }

    @GetMapping("/api/v1/employee")
    public List<EmployeeResponse> findAllEmployee() {
        return employeeService.findAllEmployee();
    }
}
