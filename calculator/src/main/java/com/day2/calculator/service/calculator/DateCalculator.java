package com.group.libraryapp.service.calculator;

import com.group.libraryapp.dto.calculator.request.DateCalcRequest;

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
