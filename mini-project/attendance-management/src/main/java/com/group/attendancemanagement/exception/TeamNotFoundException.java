package com.group.attendancemanagement.exception;

import java.util.NoSuchElementException;

public class TeamNotFoundException extends NoSuchElementException {

    public TeamNotFoundException(String message) {
        super(message);
    }
}
