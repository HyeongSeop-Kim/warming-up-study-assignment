package com.day2.calculator.contorller;

import com.day2.calculator.dto.calculator.request.AllNumberCalcRequest;
import com.day2.calculator.dto.calculator.request.DateCalcRequest;
import com.day2.calculator.dto.calculator.request.TwoNumberCalcRequest;
import com.day2.calculator.dto.calculator.response.DateCalcResponse;
import com.day2.calculator.dto.calculator.response.NumberCalcResponse;
import com.day2.calculator.service.calculator.DateCalculator;
import com.day2.calculator.service.calculator.NumberCalculator;
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
