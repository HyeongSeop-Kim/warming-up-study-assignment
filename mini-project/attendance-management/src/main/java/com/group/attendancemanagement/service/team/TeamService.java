package com.group.attendancemanagement.service.team;

import com.group.attendancemanagement.domain.employee.Employee;
import com.group.attendancemanagement.domain.employee.EmployeeRepository;
import com.group.attendancemanagement.domain.employee.enums.Role;
import com.group.attendancemanagement.domain.team.Team;
import com.group.attendancemanagement.domain.team.TeamRepository;
import com.group.attendancemanagement.dto.team.request.TeamCreateRequest;
import com.group.attendancemanagement.dto.team.response.TeamResponse;
import com.group.attendancemanagement.exception.DuplicateTeamNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamResponse> findAllTeam() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream().map(team -> {
            Optional<Employee> manager = team.getEmployees().stream()
                    .filter(employee -> employee.getRole() == Role.MANAGER)
                    .findFirst();

            return TeamResponse.builder()
                    .name(team.getName())
                    .manager(manager.map(Employee::getName).orElse(null))
                    .memberCount(team.getEmployees().size())
                    .build();
        }).toList();
    }

    @Transactional
    public void createTeam(TeamCreateRequest request) {
        if (teamRepository.existsByName(request.getName())) {
            throw new DuplicateTeamNameException("이미 존재하는 팀 이름입니다.");
        }
        teamRepository.save(Team.create(request.getName()));
    }
}
