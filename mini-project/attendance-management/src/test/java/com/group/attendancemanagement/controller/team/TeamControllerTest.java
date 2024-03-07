package com.group.attendancemanagement.controller.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.dto.team.request.TeamCreateRequest;
import com.group.attendancemanagement.dto.team.response.TeamResponse;
import com.group.attendancemanagement.service.team.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeamService teamService;

    @DisplayName("팀을 등록한다.")
    @Test
    void createTeam() throws Exception {
        // given
        TeamCreateRequest request = TeamCreateRequest.builder()
                .name("생산팀").build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/team")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName("팀을 등록할 때, 팀 이름이 null인 경우 예외를 발생시킨다.")
    @Test
    void createTeamWithNullName() throws Exception {
        // given
        TeamCreateRequest request = TeamCreateRequest.builder()
                .name(null).build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/team")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("팀을 등록할 때, 팀 이름이 공백인 경우 예외를 발생시킨다.")
    @Test
    void createTeamWithBlankName() throws Exception {
        // given
        TeamCreateRequest request = TeamCreateRequest.builder()
                .name("").build();

        // when
        // then
        mockMvc.perform(
                        post("/api/v1/team")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @DisplayName("모든 팀을 조회한다.")
    @Test
    void findAllTeam() throws Exception {
        // given
        TeamResponse response1 = TeamResponse.builder()
                .name("생산팀")
                .manager("김철수")
                .memberCount(4)
                .build();

        TeamResponse response2 = TeamResponse.builder()
                .name("영업팀")
                .manager("홍길동")
                .memberCount(2)
                .build();

        TeamResponse response3 = TeamResponse.builder()
                .name("개발팀")
                .manager("이영희")
                .memberCount(3)
                .build();

        List<TeamResponse> result = List.of(response1, response2, response3);

        when(teamService.findAllTeam()).thenReturn(result);
        // when
        // then
        mockMvc.perform(
                        get("/api/v1/team")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("생산팀"))
                .andExpect(jsonPath("$[0].manager").value("김철수"))
                .andExpect(jsonPath("$[0].memberCount").value(4))
                .andExpect(jsonPath("$[1].name").value("영업팀"))
                .andExpect(jsonPath("$[1].manager").value("홍길동"))
                .andExpect(jsonPath("$[1].memberCount").value(2))
                .andExpect(jsonPath("$[2].name").value("개발팀"))
                .andExpect(jsonPath("$[2].manager").value("이영희"))
                .andExpect(jsonPath("$[2].memberCount").value(3));
    }
}
