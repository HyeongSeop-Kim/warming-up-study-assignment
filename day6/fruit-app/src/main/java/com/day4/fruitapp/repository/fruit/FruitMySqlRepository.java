package com.day4.fruitapp.repository.fruit;

import com.day4.fruitapp.domain.fruit.Fruit;
import com.day4.fruitapp.dto.fruit.model.FruitStat;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class FruitMySqlRepository implements FruitRepository {

    private final JdbcTemplate jdbcTemplate;

    public FruitMySqlRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static RowMapper<Fruit> fruitRowMapper() {
        return (rs, rowNum) -> new Fruit(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getDate("warehousing_date").toLocalDate(),
                rs.getString("sold_yn")
        );
    }

    private static RowMapper<FruitStat> fruitStatRowMapper() {
        return (rs, rowNum) -> new FruitStat(
                rs.getString("sold_yn"),
                rs.getLong("amount")
        );
    }

    @Override
    public void save(Fruit fruit) {
        String sql = "insert into fruit (name, warehousing_date, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, fruit.getName(), fruit.getWarehousingDate(), fruit.getPrice());
    }

    @Override
    public Optional<Fruit> findById(long id) {
        String sql = "select * from fruit where id = ?";
        Fruit fruit = jdbcTemplate.queryForObject(sql, new Object[]{id}, fruitRowMapper());

        return Optional.ofNullable(fruit);
    }

    @Override
    public List<Fruit> findAllByName(String name) {
        String sql = "select * from fruit where name = ?";

        return jdbcTemplate.query(sql, new Object[]{name}, fruitRowMapper());
    }

    @Override
    public void sell(long id) {
        String sql = "update fruit set sold_yn = 'Y' where id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<FruitStat> getStats(String name) {
        String sql = "select sold_yn, sum(price) as amount from fruit where name = ? group by sold_yn";
        return jdbcTemplate.query(sql, new Object[]{name}, fruitStatRowMapper());
    }
}
