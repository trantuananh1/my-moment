package com.hunganh.mymoment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
public class MyMomentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMomentApplication.class, args);
    }
}
