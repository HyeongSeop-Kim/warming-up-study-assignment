package com.group.attendancemanagement.dto.employee.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EmployeeCreateRequest {

    @NotBlank
    private String name;

    @Positive
    private long teamId;

    @NotNull
    @Past
    private LocalDate birthday;

    @Builder
    public EmployeeCreateRequest(String name, long teamId, LocalDate birthday) {
        this.name = name;
        this.teamId = teamId;
        this.birthday = birthday;
    }
}
