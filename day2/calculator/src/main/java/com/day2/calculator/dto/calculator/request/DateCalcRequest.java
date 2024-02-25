package com.day2.calculator.dto.calculator.request;

public class DateCalcRequest {
    private String date;

    public DateCalcRequest(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
