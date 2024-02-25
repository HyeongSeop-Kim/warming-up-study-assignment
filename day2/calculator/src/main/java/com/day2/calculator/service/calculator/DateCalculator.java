package com.day2.calculator.service.calculator;


import com.day2.calculator.dto.calculator.request.DateCalcRequest;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateCalculator {
    public String dayOfTheWeek(DateCalcRequest request) {
        return LocalDate.parse(request.getDate()).getDayOfWeek()
                .getDisplayName(TextStyle.SHORT, Locale.US)
                .toUpperCase();
    }
}
