package com.group.attendancemanagement.domain.team;

import com.group.attendancemanagement.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees = new ArrayList<>();

    @Builder
    private Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Team create(String name) {
        return Team.builder().name(name).build();
    }

}