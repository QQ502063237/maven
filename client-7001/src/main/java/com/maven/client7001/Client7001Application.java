package com.maven.client7001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.maven.client7001.mapper")
public class Client7001Application {

    public static void main(String[] args) {
        SpringApplication.run(Client7001Application.class, args);
    }

}
