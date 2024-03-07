package com.group.attendancemanagement.domain.employee;

import com.group.attendancemanagement.domain.employee.enums.Role;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.domain.team.TeamRepository;
import com.group.attendancemanagement.dto.employee.request.EmployeeCreateRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("직원을 등록한다.")
    @Test
    void saveEmployee() {
        // given
        Team team = Team.create("생산팀");
        teamRepository.save(team);

        LocalDate birthday = LocalDate.of(1990, Month.APRIL, 1);
        LocalDate workStartDate = LocalDate.now();
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(1L)
                .birthday(birthday)
                .build();
        Employee employee = Employee.create(request, team, workStartDate);

        // when
        Employee result = employeeRepository.save(employee);

        // then
        Assertions.assertThat(result)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(employee);
    }

    @DisplayName("모든 직원을 조회한다.")
    @Test
    void findAll() {
        // given
        Team team1 = Team.create("생산팀");
        Team team2 = Team.create("영업팀");
        teamRepository.saveAll(List.of(team1, team2));

        LocalDate birthday1 = LocalDate.of(1990, Month.APRIL, 1);
        LocalDate workStartDate1 = LocalDate.now();
        EmployeeCreateRequest request1 = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(1L)
                .birthday(birthday1)
                .build();
        Employee employee1 = Employee.create(request1, team1, workStartDate1);

        LocalDate birthday2 = LocalDate.of(1991, Month.MAY, 2);
        LocalDate workStartDate2 = LocalDate.of(2024, Month.JANUARY, 2);
        EmployeeCreateRequest request2 = EmployeeCreateRequest.builder()
                .name("김철수")
                .teamId(1L)
                .birthday(birthday2)
                .build();
        Employee employee2 = Employee.create(request2, team1, workStartDate2);

        LocalDate birthday3 = LocalDate.of(1992, Month.JUNE, 3);
        LocalDate workStartDate3 = LocalDate.of(2023, Month.FEBRUARY, 22);
        EmployeeCreateRequest request3 = EmployeeCreateRequest.builder()
                .name("이영희")
                .teamId(2L)
                .birthday(birthday3)
                .build();
        Employee employee3 = Employee.create(request3, team2, workStartDate3);

        employeeRepository.saveAll(List.of(employee1, employee2, employee3));

        // when
        List<Employee> result = employeeRepository.findAll();

        // then
        assertThat(result).hasSize(3)
                .extracting("id", "name", "team", "role", "birthday", "workStartDate")
                .containsExactlyInAnyOrder(
                        tuple(1L, "홍길동", team1, Role.MEMBER, birthday1, workStartDate1),
                        tuple(2L, "김철수", team1, Role.MEMBER, birthday2, workStartDate2),
                        tuple(3L, "이영희", team2, Role.MEMBER, birthday3, workStartDate3)
                );
    }
}
