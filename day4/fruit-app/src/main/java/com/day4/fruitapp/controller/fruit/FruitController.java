package com.day4.fruitapp.controller.fruit;

import com.day4.fruitapp.dto.fruit.model.FruitStat;
import com.day4.fruitapp.dto.fruit.request.FruitSellReqeust;
import com.day4.fruitapp.dto.fruit.request.FruitStoreRequest;
import com.day4.fruitapp.dto.fruit.response.FruitStatResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;

    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/v1/fruit")
    public void storeFruit(@RequestBody FruitStoreRequest request) {
        String sql = "insert into fruit (name, warehousing_date, price) values (?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());
    }

    @PutMapping("/api/v1/fruit")
    public void sellFruit(@RequestBody FruitSellReqeust reqeust) {
        String sql = "update fruit set sold_yn = 'Y' where id = ?";
        jdbcTemplate.update(sql, reqeust.getId());
    }

    @GetMapping("/api/v1/fruit/stat")
    public FruitStatResponse getStat(@RequestParam String name) {
        String sql = "select sold_yn, sum(price) as amount from fruit where name = ? group by sold_yn";
        List<FruitStat> stats = jdbcTemplate.query(sql,
                new Object[]{name},
                (rs, rowNum) -> new FruitStat(rs.getString("sold_yn"), rs.getLong("amount")));

        FruitStatResponse response = new FruitStatResponse();
        stats.forEach(response::setStat);
        return response;
    }
}
