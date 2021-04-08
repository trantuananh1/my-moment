package com.hunganh.mymoment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories
@EnableAsync
@EnableNeo4jRepositories
public class MyMomentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMomentApplication.class, args);
    }
}
