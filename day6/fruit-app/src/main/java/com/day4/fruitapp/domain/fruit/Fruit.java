package com.day4.fruitapp.domain.fruit;

import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Fruit {

    @Id
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private long price;

    @Column(name = "warehousing_date")
    private LocalDate warehousingDate;

    @Column(name = "sold_yn", length = 1)
    private String soldYn;

    public Fruit() {}

    public Fruit(FruitAddRequest request) {
        this.name = request.getName();
        this.price = request.getPrice();
        this.warehousingDate = request.getWarehousingDate();
        this.soldYn = "N";
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

    public boolean isSold() {
        return this.soldYn.equals("Y");
    }
}
