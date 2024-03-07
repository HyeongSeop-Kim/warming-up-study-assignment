package com.group.attendancemanagement.domain.employee;

import com.group.attendancemanagement.domain.employee.enums.Role;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.dto.employee.request.EmployeeCreateRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Team team;

    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private LocalDate workStartDate;

    @Builder
    private Employee(String name, Team team, LocalDate birthday, LocalDate workStartDate) {
        this.name = name;
        this.team = team;
        this.role = Role.MEMBER;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }

    public static Employee create(EmployeeCreateRequest request, Team team, LocalDate workStartDate) {
        return Employee.builder()
                .name(request.getName())
                .team(team)
                .birthday(request.getBirthday())
                .workStartDate(workStartDate)
                .build();
    }

    public void assignManager() {
        this.role = Role.MANAGER;
    }
}
