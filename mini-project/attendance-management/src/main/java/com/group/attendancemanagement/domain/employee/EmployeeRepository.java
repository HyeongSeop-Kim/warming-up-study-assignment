package com.group.attendancemanagement.domain.employee;

import com.group.attendancemanagement.domain.employee.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByRoleAndTeamId(Role role, Long teamId);
}
