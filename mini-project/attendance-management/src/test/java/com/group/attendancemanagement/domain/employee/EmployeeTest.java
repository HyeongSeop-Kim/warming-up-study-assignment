package com.group.attendancemanagement.domain.employee;

import com.group.attendancemanagement.domain.employee.enums.Role;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.dto.employee.request.EmployeeCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.*;

class EmployeeTest {

    @DisplayName("직원을 팀의 매니저로 지정한다.")
    @Test
    void assignManager() {
        // given
        LocalDate birthday = LocalDate.of(1990, Month.APRIL, 1);
        LocalDate workStartDate = LocalDate.now();
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(1L)
                .birthday(birthday)
                .build();
        Employee employee = Employee.create(request, Team.builder().build(), workStartDate);

        // when
        employee.assignManager();

        // then
        assertThat(employee.getRole()).isEqualTo(Role.MANAGER);
    }
}
