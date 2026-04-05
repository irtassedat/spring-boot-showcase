package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.FruitRespoonse;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.services.FruitService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/fruit")
public class FruitController {
    private final FruitService fruitService;

    @GetMapping
    public List<Fruit> get() {
        return fruitService.getByPriceAsc();
    }

    @GetMapping("/priceAsc")
    public List<Fruit> getDesc() {
        return fruitService.getByPriceDesc();
    }

    @GetMapping("/{id}")
    public FruitRespoonse get(@Positive @PathVariable("id") Long id) {

        return new FruitRespoonse("success", fruitService.getById(id));
    }

    @GetMapping("/name/{name}")
    public List<Fruit> getByName( @Size(min = 2 , max = 45, message = "Name must be between 1 and 45 characters") @PathVariable("name") String name) {
        return fruitService.searchByName(name);
    }

    @PostMapping
    public Fruit save(@Validated @RequestBody Fruit fruit) {
        return fruitService.save(fruit);
    }

    @DeleteMapping("/{id}")
    public Fruit delete(@NotNull @Positive @PathVariable Long id) {
        return fruitService.delete(id);
    }


}
