package com.group.attendancemanagement.service.team;

import com.group.attendancemanagement.domain.team.TeamRepository;
import com.group.attendancemanagement.dto.team.request.TeamCreateRequest;
import com.group.attendancemanagement.exception.DuplicateTeamNameException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class TeamServiceTest {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(teamRepository);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("팀을 등록한다.")
    @Test
    void createTeam() {
        // given
        TeamCreateRequest request = TeamCreateRequest.builder().name("생산팀").build();
        when(teamRepository.existsByName(request.getName())).thenReturn(false);

        // when
        teamService.createTeam(request);

        // then
        verify(teamRepository, times(1)).existsByName(anyString());
        verify(teamRepository, times(1)).save(any());
    }

    @DisplayName("중복된 팀 이름으로 등록하면 예외가 발생한다.")
    @Test
    void createTeamWithDuplicateName() {
        // given
        TeamCreateRequest request = TeamCreateRequest.builder().name("생산팀").build();
        when(teamRepository.existsByName(request.getName())).thenReturn(true);

        // when, then
        assertThrows(DuplicateTeamNameException.class, () -> teamService.createTeam(request));
        verify(teamRepository, times(1)).existsByName(anyString());
        verify(teamRepository, never()).save(any());
    }

    @DisplayName("모든 팀을 조회한다.")
    @Test
    void findAllTeam() {
        // given

        // when

        // then
    }
}
