package com.group.libraryapp.dto.calculator.response;

public class NumberCalcResponse {
    private Integer add;
    private Integer minus;
    private Integer multiply;

    public NumberCalcResponse(Integer add, Integer minus, Integer multiply) {
        this.add = add;
        this.minus = minus;
        this.multiply = multiply;
    }

    public Integer getAdd() {
        return add;
    }

    public Integer getMinus() {
        return minus;
    }

    public Integer getMultiply() {
        return multiply;
    }
}