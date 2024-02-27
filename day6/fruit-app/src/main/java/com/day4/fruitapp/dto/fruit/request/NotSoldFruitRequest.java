package com.day4.fruitapp.dto.fruit.request;

import com.day4.fruitapp.dto.fruit.enums.PriceComparison;

public class NotSoldFruitRequest {

    private PriceComparison option;
    private long price;

    public NotSoldFruitRequest(PriceComparison option, long price) {
        this.option = option;
        this.price = price;
    }

    public PriceComparison getOption() {
        return option;
    }

    public long getPrice() {
        return price;
    }
}
