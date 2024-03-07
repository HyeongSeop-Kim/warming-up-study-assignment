package com.group.attendancemanagement.domain.team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void tearDown() {
        teamRepository.deleteAllInBatch();
    }

    @DisplayName("팀을 등록한다.")
    @Test
    void save() {
        // given
        Team team = Team.create("생산팀");

        // when
        Team result = teamRepository.save(team);

        // then
        assertThat(result.getName()).isEqualTo(team.getName());
    }

    @DisplayName("모든 팀을 조회한다.")
    @Test
    void findAll() {
        // given
        Team team1 = Team.create("생산팀");
        Team team2 = Team.create("영업팀");
        Team team3 = Team.create("재정팀");

        teamRepository.saveAll(List.of(team1, team2, team3));

        // when
        List<Team> result = teamRepository.findAll();

        // then
        assertThat(result).hasSize(3)
                .extracting("id", "name")
                .containsExactlyInAnyOrder(
                        tuple(1L, "생산팀"),
                        tuple(2L, "영업팀"),
                        tuple(3L, "재정팀")
                );
    }
}
