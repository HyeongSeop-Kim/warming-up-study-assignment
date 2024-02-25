package com.group.libraryapp.dto.calculator.response;

public class DateCalcResponse {
    private String dayOfTheWeek;

    public DateCalcResponse(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}
