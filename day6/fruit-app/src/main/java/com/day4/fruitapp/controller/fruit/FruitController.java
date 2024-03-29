package com.day4.fruitapp.controller.fruit;

import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.request.NotSoldFruitRequest;
import com.day4.fruitapp.dto.fruit.response.FruitCountResponse;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import com.day4.fruitapp.dto.fruit.response.NotSoldFruitResoponse;
import com.day4.fruitapp.service.fruit.FruitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public void storeFruit(@RequestBody FruitAddRequest request) {
        fruitService.storeFruit(request);
    }

    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitSellReqeust reqeust) {
        fruitService.sellFruit(reqeust);
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse getStat(@RequestParam String name) {
        return fruitService.getStat(name);
    }

    @GetMapping("/api/v1/fruit/count")
    public FruitCountResponse getCount(@RequestParam String name) {
        return fruitService.getCount(name);
    }

    @GetMapping("/api/v1/fruit/list")
    public List<NotSoldFruitResoponse> getNotSoldFruits(NotSoldFruitRequest request) {
        return fruitService.getNotSoldFruits(request);
    }
}
