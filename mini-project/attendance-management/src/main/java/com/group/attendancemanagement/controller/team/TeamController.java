package com.group.attendancemanagement.controller.team;

import com.group.attendancemanagement.dto.team.request.TeamCreateRequest;
import com.group.attendancemanagement.dto.team.response.TeamResponse;
import com.group.attendancemanagement.service.team.TeamService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/api/v1/team")
    public void createTeam(@Valid @RequestBody TeamCreateRequest request) {
        teamService.createTeam(request);
    }

    @GetMapping("/api/v1/team")
    public List<TeamResponse> findAllTeam() {
        return teamService.findAllTeam();
    }
}
