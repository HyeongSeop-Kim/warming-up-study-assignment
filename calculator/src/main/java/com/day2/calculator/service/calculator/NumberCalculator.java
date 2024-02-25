package com.group.libraryapp.service.calculator;

import com.group.libraryapp.dto.calculator.request.AllNumberCalcRequest;
import com.group.libraryapp.dto.calculator.request.TwoNumberCalcRequest;

import java.util.List;

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
