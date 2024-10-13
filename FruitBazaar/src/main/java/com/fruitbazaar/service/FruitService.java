package com.fruitbazaar.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fruitbazaar.mapper.EmployeeMapper;
import com.fruitbazaar.mapper.FruitMapper;
import com.fruitbazaar.model.entity.Fruit;
import com.fruitbazaar.service.interf.IFruitService;
import org.springframework.stereotype.Service;

@Service
public class FruitService extends ServiceImpl<FruitMapper, Fruit> implements IFruitService {
//    @Autowired
//    private FruitMapper fruitMapper;
//    @Override
//    public List<Fruit> Query() {
//        List<Fruit> data=fruitMapper.Query();
//        return data;
//    }
}
