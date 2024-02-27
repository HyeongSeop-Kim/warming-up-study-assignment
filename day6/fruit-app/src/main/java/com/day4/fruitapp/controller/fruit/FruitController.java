package com.day4.fruitapp.controller.fruit;

import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import com.day4.fruitapp.service.fruit.FruitServiceV1;
import org.springframework.web.bind.annotation.*;


@RestController
public class FruitController {

    private final FruitServiceV1 fruitServiceV1;

    public FruitController(FruitServiceV1 fruitServiceV1) {
        this.fruitServiceV1 = fruitServiceV1;
    }

    @PostMapping("/api/v1/fruit")
    public void storeFruit(@RequestBody FruitAddRequest request) {
        fruitServiceV1.storeFruit(request);
    }

    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitSellReqeust reqeust) {
        fruitServiceV1.sellFruit(reqeust);
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse getStat(@RequestParam String name) {
        return fruitServiceV1.getStat(name);
    }
}
