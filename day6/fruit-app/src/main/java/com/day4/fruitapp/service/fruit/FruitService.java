package com.day4.fruitapp.service.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import com.day4.fruitapp.dto.fruit.model.FruitStat;
import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import com.day4.fruitapp.exception.FruitNotFoundException;
import com.day4.fruitapp.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    private final FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void storeFruit(FruitAddRequest request) {
        fruitRepository.save(new Fruit(request));
    }

    public void sellFruit(FruitSellReqeust reqeust) {
        Fruit fruit = fruitRepository.findById(reqeust.getId())
                .orElseThrow(() -> new FruitNotFoundException("과일 정보를 찾을 수 없습니다."));

        fruitRepository.sell(fruit.getId());
    }

    public FruitStatResponse getStat(String name) {
        if (fruitRepository.findAllByName(name).isEmpty()) {
            throw new FruitNotFoundException("과일 정보를 찾을 수 없습니다.");
        }

        List<FruitStat> stats = fruitRepository.getStats(name);
        FruitStatResponse response = new FruitStatResponse();
        stats.forEach(response::setStat);

        return response;
    }
}
