package com.day4.fruitapp.dto.fruit.model;

public class FruitStat {
    private String soldYn;
    private long amount;

    public FruitStat(String soldYn, long amount) {
        this.soldYn = soldYn;
        this.amount = amount;
    }

    public String getSoldYn() {
        return soldYn;
    }

    public long getAmount() {
        return amount;
    }
}
