package com.hunganh.mymoment;

import com.hunganh.mymoment.model.User;
import com.hunganh.mymoment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class MyMomentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyMomentApplication.class, args);
    }
}
