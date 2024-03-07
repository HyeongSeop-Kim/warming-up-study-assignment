package com.group.attendancemanagement.domain.team;

import com.group.attendancemanagement.dto.team.response.TeamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByName(String name);
}
