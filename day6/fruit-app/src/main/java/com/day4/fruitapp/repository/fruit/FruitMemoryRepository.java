package com.day4.fruitapp.repository.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import com.day4.fruitapp.dto.fruit.model.FruitStat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FruitMemoryRepository implements FruitRepository {

    private static final List<Fruit> fruits = new ArrayList<>();

    private static void validateId(long id) {
        if(id < 0 || id > fruits.size()) {
            throw new IllegalArgumentException("잘못된 ID 입니다.");
        }
    }

    @Override
    public void save(Fruit fruit) {
        fruits.add(new Fruit(
                fruits.size() + 1L,
                fruit.getName(),
                fruit.getPrice(),
                fruit.getWarehousingDate(),
                "N"));
    }

    @Override
    public Optional<Fruit> findById(long id) {
        validateId(id);
        return Optional.of(fruits.get((int) id - 1));
    }

    @Override
    public List<Fruit> findAllByName(String name) {
        return fruits.stream()
                .filter(fruit -> name.equals(fruit.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void sell(long id) {
        if(id < 0 || id > fruits.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Fruit fruit = findById(id).get();
        fruit.sell();
        fruits.set((int) id - 1, fruit);
    }

    @Override
    public List<FruitStat> getStats(String name) {
        return fruits.stream()
                .filter(fruit -> name.equals(fruit.getName()))
                .collect(Collectors.groupingBy(Fruit::getSoldYn, Collectors.summingLong(Fruit::getPrice)))
                .entrySet().stream()
                .map(entry -> new FruitStat(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
