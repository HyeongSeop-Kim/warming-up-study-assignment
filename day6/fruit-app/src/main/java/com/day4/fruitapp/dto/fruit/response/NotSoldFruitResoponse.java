package com.day4.fruitapp.dto.fruit.response;

import com.day4.fruitapp.domain.fruit.Fruit;

import java.time.LocalDate;

public class NotSoldFruitResoponse {

    private String name;
    private long price;
    private LocalDate warehousingDate;

    public NotSoldFruitResoponse(Fruit fruit) {
        this.name = fruit.getName();
        this.price = fruit.getPrice();
        this.warehousingDate = fruit.getWarehousingDate();
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }
}
