package com.passboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestPassboardApplication {

    public static void main(String[] args) {
        SpringApplication.from(PassboardApplication::main).with(TestPassboardApplication.class).run(args);
    }

}
