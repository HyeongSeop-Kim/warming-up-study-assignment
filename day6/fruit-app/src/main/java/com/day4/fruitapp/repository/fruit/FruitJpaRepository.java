package com.day4.fruitapp.repository.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FruitJpaRepository extends JpaRepository<Fruit, Long> {

    List<Fruit> findAllByName(String name);
}
