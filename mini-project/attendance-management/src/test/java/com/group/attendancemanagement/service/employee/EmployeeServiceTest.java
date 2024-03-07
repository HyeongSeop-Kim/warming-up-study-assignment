package com.group.attendancemanagement.service.employee;

import com.group.attendancemanagement.domain.employee.Employee;
import com.group.attendancemanagement.domain.employee.EmployeeRepository;
import com.group.attendancemanagement.domain.employee.enums.Role;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.domain.team.TeamRepository;
import com.group.attendancemanagement.dto.employee.request.EmployeeCreateRequest;
import com.group.attendancemanagement.dto.employee.response.EmployeeResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("직원을 등록한다.")
    @Test
    void createEmployee() {
        // given
        when(teamRepository.findById(1L)).thenReturn(Optional.of(Team.create("생산팀")));

        LocalDate workStartDate = LocalDate.now();
        LocalDate birthday = LocalDate.parse("1999-01-01");
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(1L)
                .birthday(birthday)
                .build();

        // when
        employeeService.createEmployee(request, workStartDate);

        // then
        verify(employeeRepository, times(1)).save(any());
    }

    @DisplayName("모든 직원을 조회한다.")
    @Test
    void findAllEmployee() {
        // given
        Team team1 = Team.create("생산팀");
        Team team2 = Team.create("영업팀");
        when(teamRepository.findAll()).thenReturn(List.of(team1, team2));

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
        when(employeeRepository.findAll()).thenReturn(List.of(employee1, employee2, employee3));

        // when
        List<EmployeeResponse> result = employeeService.findAllEmployee();

        // then
        assertThat(result).hasSize(3)
                .extracting("name", "teamName", "role", "birthday", "workStartDate")
                .containsExactlyInAnyOrder(
                        tuple("홍길동", "생산팀", Role.MEMBER, birthday1, workStartDate1),
                        tuple("김철수", "생산팀", Role.MEMBER, birthday2, workStartDate2),
                        tuple("이영희", "영업팀", Role.MEMBER, birthday3, workStartDate3)
                );
    }
}
