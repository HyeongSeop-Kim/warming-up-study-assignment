package com.group.attendancemanagement.exception;

public class DuplicateTeamNameException extends IllegalArgumentException {
    public DuplicateTeamNameException(String message) {
        super(message);
    }
}
