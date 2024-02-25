package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.service.calculator.DateCalculator;
import com.group.libraryapp.service.calculator.NumberCalculator;
import com.group.libraryapp.dto.calculator.request.AllNumberCalcRequest;
import com.group.libraryapp.dto.calculator.request.DateCalcRequest;
import com.group.libraryapp.dto.calculator.request.TwoNumberCalcRequest;
import com.group.libraryapp.dto.calculator.response.DateCalcResponse;
import com.group.libraryapp.dto.calculator.response.NumberCalcResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final NumberCalculator numCalc = new NumberCalculator();
    private final DateCalculator dateCalc = new DateCalculator();

    @GetMapping("/api/v1/calc")
    public NumberCalcResponse calcTwoNumbers(TwoNumberCalcRequest request) {
        return new NumberCalcResponse(
                numCalc.add(request),
                numCalc.minus(request),
                numCalc.multiply(request)
        );
    }

    @GetMapping("/api/v1/day-calc")
    public DateCalcResponse calcDayOfTheWeek(DateCalcRequest request) {
        return new DateCalcResponse(dateCalc.dayOfTheWeek(request));
    }

    @PostMapping("/api/v1/calc")
    public Integer addAllNumbers(@RequestBody AllNumberCalcRequest request) {
        return numCalc.addAll(request);
    }
}
