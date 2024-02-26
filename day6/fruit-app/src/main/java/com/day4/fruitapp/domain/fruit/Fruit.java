package com.day4.fruitapp.domain.fruit;

import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;

import java.time.LocalDate;

public class Fruit {

    private long id;

    private String name;

    private long price;

    private LocalDate warehousingDate;

    private String soldYn;

    public Fruit() {}

    public Fruit(FruitAddRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.warehousingDate = request.getWarehousingDate();
    }

    public Fruit(long id, String name, long price, LocalDate warehousingDate, String soldYn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.warehousingDate = warehousingDate;
        this.soldYn = soldYn;
    }

    public long getId() {
        return id;
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

    public String getSoldYn() {
        return soldYn;
    }

    public void sell() {
        this.soldYn = "Y";
    }
}
