package com.day4.fruitapp.service.fruit;

import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.request.NotSoldFruitRequest;
import com.day4.fruitapp.dto.fruit.response.FruitCountResponse;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import com.day4.fruitapp.dto.fruit.response.NotSoldFruitResoponse;

import java.util.List;

public interface FruitService {
    void storeFruit(FruitAddRequest request);
    void sellFruit(FruitSellReqeust reqeust);
    FruitStatResponse getStat(String name);
    FruitCountResponse getCount(String name);
    List<NotSoldFruitResoponse> getNotSoldFruits(NotSoldFruitRequest request);
}
