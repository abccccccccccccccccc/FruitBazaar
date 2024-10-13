package com.fruitbazaar;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fruitbazaar.mapper")
public class FruitBazaarApplication {

    public static void main(String[] args) {
        SpringApplication.run(FruitBazaarApplication.class, args);
    }

}
