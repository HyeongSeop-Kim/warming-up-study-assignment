package com.day4.fruitapp.service.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import com.day4.fruitapp.dto.fruit.model.FruitStat;
import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import com.day4.fruitapp.exception.FruitNotFoundException;
import com.day4.fruitapp.repository.fruit.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitServiceV1 implements FruitService{

    private final FruitRepository fruitRepository;

    public FruitServiceV1(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public void storeFruit(FruitAddRequest request) {
        fruitRepository.save(new Fruit(request));
    }

    @Override
    public void sellFruit(FruitSellReqeust reqeust) {
        Fruit fruit = fruitRepository.findById(reqeust.getId())
                .orElseThrow(() -> new FruitNotFoundException("과일 정보를 찾을 수 없습니다."));

        if (fruit.isSold()) {
            throw new FruitNotFoundException("이미 팔린 과일입니다.");
        }

        fruitRepository.sell(fruit.getId());
    }

    @Override
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
