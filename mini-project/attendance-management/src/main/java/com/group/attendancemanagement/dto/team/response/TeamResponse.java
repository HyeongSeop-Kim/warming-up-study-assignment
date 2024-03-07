package com.group.attendancemanagement.dto.team.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class TeamResponse {

    private String name;

    private String manager;

    private long memberCount;

    @Builder
    public TeamResponse(String name, String manager, long memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }

    public TeamResponse(String name, long memberCount) {
        this.name = name;
        this.memberCount = memberCount;
    }
}
