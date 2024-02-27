package com.day4.fruitapp.repository.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import com.day4.fruitapp.dto.fruit.model.FruitStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FruitJpaRepository extends JpaRepository<Fruit, Long> {

    List<Fruit> findAllByName(String name);

    @Query("select new com.day4.fruitapp.dto.fruit.model.FruitStat(f.soldYn, sum(f.price)) from Fruit f where f.name = :name group by f.soldYn")
    List<FruitStat> getStats(@Param("name") String name);
}
