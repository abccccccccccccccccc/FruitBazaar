package com.fruitbazaar.controller;

import com.fruitbazaar.common.result.Result;
import com.fruitbazaar.model.entity.Fruit;
import com.fruitbazaar.service.interf.IFruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/fruit")
@RestController
public class FruitController {
    @Autowired
    private IFruitService fruitService;

    @GetMapping("/list")
    public Result FruitList() {
        List<Fruit> data=fruitService.list();
        return Result.success(data);
    }

}
