package com.day4.fruitapp.service.fruit;

import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;

public interface FruitService {
    void storeFruit(FruitAddRequest request);
    void sellFruit(FruitSellReqeust reqeust);
    FruitStatResponse getStat(String name);
}
