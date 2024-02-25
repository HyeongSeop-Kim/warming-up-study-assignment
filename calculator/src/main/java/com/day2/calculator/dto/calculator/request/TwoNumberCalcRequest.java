package com.group.libraryapp.dto.calculator.request;

import java.util.List;

public class TwoNumberCalcRequest {

    private Integer num1;
    private Integer num2;

    public TwoNumberCalcRequest(Integer num1, Integer num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public Integer getNum1() {
        return num1;
    }

    public Integer getNum2() {
        return num2;
    }

}
