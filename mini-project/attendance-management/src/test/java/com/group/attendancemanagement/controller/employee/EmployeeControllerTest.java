package com.group.attendancemanagement.controller.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.attendancemanagement.domain.employee.Employee;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.dto.employee.request.EmployeeCreateRequest;
import com.group.attendancemanagement.dto.employee.response.EmployeeResponse;
import com.group.attendancemanagement.service.employee.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @DisplayName("직원을 등록한다.")
    @Test
    void createEmployee() throws Exception {
        // given
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(1L)
                .birthday(LocalDate.parse("1999-01-01"))
                .build();

        // when
        // then
        mockMvc.perform(
                    post("/api/v1/employee")
                            .content(objectMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("직원을 등록할 때, 직원 이름이 null인 경우 예외를 발생한다.")
    @Test
    void createEmployeeWithNullName() throws Exception {
        // given
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name(null)
                .teamId(1L)
                .birthday(LocalDate.parse("1999-01-01"))
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/employee")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("직원을 등록할 때, 직원 이름이 공백인 경우 예외를 발생한다.")
    @Test
    void createEmployeeWithBlankName() throws Exception {
        // given
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("")
                .teamId(1L)
                .birthday(LocalDate.parse("1999-01-01"))
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/employee")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("직원을 등록할 때, 팀 ID가 양수가 아닐 경우 예외를 발생한다.")
    @Test
    void createEmployeeWithNotPositiveTeamId() throws Exception {
        // given
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(0)
                .birthday(LocalDate.parse("1999-01-01"))
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/employee")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("직원을 등록할 때, 생일이 null인 경우 예외를 발생한다.")
    @Test
    void createEmployeeWithNullBirthday() throws Exception {
        // given
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(1L)
                .birthday(null)
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/employee")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("직원을 등록할 때, 생일이 오늘 날짜보다 큰 경우 예외를 발생한다.")
    @Test
    void createEmployeeWithNotPastBirthday() throws Exception {
        // given
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("홍길동")
                .teamId(1L)
                .birthday(LocalDate.parse("9999-12-31"))
                .build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/employee")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("모든 직원을 조회한다.")
    @Test
    void findAllEmployee() throws Exception {
        // given
        Employee employee1 = Employee.builder()
                .name("홍길동")
                .team(Team.builder().name("생산팀").build())
                .birthday(LocalDate.parse("2023-01-01"))
                .workStartDate(LocalDate.parse("1990-01-01"))
                .build();

        Employee employee2 = Employee.builder()
                .name("김철수")
                .team(Team.builder().name("영업팀").build())
                .birthday(LocalDate.parse("2002-12-01"))
                .workStartDate(LocalDate.parse("1999-01-01"))
                .build();
        employee2.assignManager();

        List<EmployeeResponse> employees = List.of(
                new EmployeeResponse(employee1, "생산팀"),
                new EmployeeResponse(employee2, "영업팀")
        );

        when(employeeService.findAllEmployee()).thenReturn(employees);

        // when, then
        mockMvc.perform(get("/api/v1/employee"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("홍길동"))
                .andExpect(jsonPath("$[0].teamName").value("생산팀"))
                .andExpect(jsonPath("$[0].birthday").value("2023-01-01"))
                .andExpect(jsonPath("$[0].role").value("MEMBER"))
                .andExpect(jsonPath("$[0].workStartDate").value("1990-01-01"))
                .andExpect(jsonPath("$[1].name").value("김철수"))
                .andExpect(jsonPath("$[1].teamName").value("영업팀"))
                .andExpect(jsonPath("$[1].birthday").value("2002-12-01"))
                .andExpect(jsonPath("$[1].role").value("MANAGER"))
                .andExpect(jsonPath("$[1].workStartDate").value("1999-01-01"));
    }
}
