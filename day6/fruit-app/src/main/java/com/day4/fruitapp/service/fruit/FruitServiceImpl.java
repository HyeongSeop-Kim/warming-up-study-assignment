package com.day4.fruitapp.service.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import com.day4.fruitapp.dto.fruit.model.FruitStat;
import com.day4.fruitapp.dto.fruit.request.FruitAddRequest;
import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.request.NotSoldFruitRequest;
import com.day4.fruitapp.dto.fruit.response.FruitCountResponse;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import com.day4.fruitapp.dto.fruit.response.NotSoldFruitResoponse;
import com.day4.fruitapp.exception.FruitNotFoundException;
import com.day4.fruitapp.repository.fruit.FruitJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitJpaRepository fruitRepository;

    public FruitServiceImpl(FruitJpaRepository fruitRepository) {
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

        fruit.sell();
        fruitRepository.save(fruit);
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

    @Override
    public FruitCountResponse getCount(String name) {
        return new FruitCountResponse(fruitRepository.countByName(name));
    }

    @Override
    public List<NotSoldFruitResoponse> getNotSoldFruits(NotSoldFruitRequest request) {
        List<Fruit> fruits = switch (request.getOption()) {
            case GTE -> fruitRepository.findAllBySoldYnAndPriceGreaterThanEqualOrderByName("N", request.getPrice());
            case LTE -> fruitRepository.findAllBySoldYnAndPriceLessThanEqualOrderByName("N", request.getPrice());
        };

        return fruits.stream().map(NotSoldFruitResoponse::new).collect(Collectors.toList());
    }
}
