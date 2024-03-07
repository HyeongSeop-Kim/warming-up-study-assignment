package com.group.attendancemanagement.dto.team.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamCreateRequest {

    @NotBlank
    private String name;

    @Builder
    public TeamCreateRequest(String name) {
        this.name = name;
    }
}
