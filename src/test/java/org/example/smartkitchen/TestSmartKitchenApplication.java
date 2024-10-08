package org.example.smartkitchen;

import org.springframework.boot.SpringApplication;

public class TestSmartKitchenApplication {

    public static void main(String[] args) {
        SpringApplication.from(SmartKitchenApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
