package com.day4.fruitapp.repository.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import com.day4.fruitapp.dto.fruit.model.FruitStat;

import java.util.List;
import java.util.Optional;

public interface FruitRepository {
    void save(Fruit fruit);

    Optional<Fruit> findById(long id);

    List<Fruit> findAllByName(String name);

    List<FruitStat> getStats(String name);

    void sell(long id);
}
