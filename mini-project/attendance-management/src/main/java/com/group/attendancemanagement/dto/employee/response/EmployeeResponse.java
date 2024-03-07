package com.group.attendancemanagement.dto.employee.response;

import com.group.attendancemanagement.domain.employee.Employee;
import com.group.attendancemanagement.domain.employee.enums.Role;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeeResponse {

    private String name;

    private String teamName;

    private Role role;

    private LocalDate birthday;

    private LocalDate workStartDate;

    public EmployeeResponse(Employee employee, String teamName) {
        this.name = employee.getName();
        this.teamName = teamName;
        this.role = employee.getRole();
        this.birthday = employee.getBirthday();
        this.workStartDate = employee.getWorkStartDate();
    }
}
