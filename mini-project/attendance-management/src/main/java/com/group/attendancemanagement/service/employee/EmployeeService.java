package com.group.attendancemanagement.service.employee;

import com.group.attendancemanagement.domain.employee.Employee;
import com.group.attendancemanagement.domain.employee.EmployeeRepository;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.domain.team.TeamRepository;
import com.group.attendancemanagement.dto.employee.request.EmployeeCreateRequest;
import com.group.attendancemanagement.dto.employee.response.EmployeeResponse;
import com.group.attendancemanagement.exception.TeamNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final TeamRepository teamRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TeamRepository teamRepository) {
        this.employeeRepository = employeeRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void createEmployee(EmployeeCreateRequest request, LocalDate workStartDate) {
        Team team = teamRepository.findById(request.getTeamId()).orElseThrow(() -> new TeamNotFoundException(String.format("id(%d)에 해당하는 팀을 찾을 수 없습니다.", request.getTeamId())));
        employeeRepository.save(Employee.create(request, team, workStartDate));
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> findAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map(employee -> new EmployeeResponse(employee, employee.getTeam().getName())).toList();
    }
}
