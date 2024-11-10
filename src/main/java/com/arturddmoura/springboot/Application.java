package com.arturddmoura.springboot;

import com.arturddmoura.springboot.contracts.Areas;
import com.arturddmoura.springboot.contracts.Contract;
import com.arturddmoura.springboot.contracts.Products;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        UUID randomUUID = UUID.randomUUID();
        Contract contract = new Contract(randomUUID, String.format("SP-%s", randomUUID), Areas.sales, Products.software_purchase, LocalDateTime.now());
        System.out.println(contract);
    }

}
