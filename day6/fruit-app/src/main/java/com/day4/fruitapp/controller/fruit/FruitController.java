package com.day4.fruitapp.controller.fruit;

import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import com.day4.fruitapp.service.fruit.FruitService;
import org.springframework.web.bind.annotation.*;


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
}
