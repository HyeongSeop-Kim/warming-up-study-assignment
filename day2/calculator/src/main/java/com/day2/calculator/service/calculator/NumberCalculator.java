package com.day2.calculator.service.calculator;

import com.day2.calculator.dto.calculator.request.AllNumberCalcRequest;
import com.day2.calculator.dto.calculator.request.TwoNumberCalcRequest;

public class NumberCalculator {
    public Integer add(TwoNumberCalcRequest request) {
        return request.getNum1() + request.getNum2();
    }

    public Integer minus(TwoNumberCalcRequest request) {
        return request.getNum1() - request.getNum2();
    }

    public Integer multiply(TwoNumberCalcRequest request) {
        return request.getNum1() * request.getNum2();
    }

    public Integer addAll(AllNumberCalcRequest request) {
        return request.getNumbers()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
