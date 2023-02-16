package com.example.segomarketnew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SegoMarketNewApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SegoMarketNewApplication.class, args);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        System.out.println(encoder.encode("pass"));

       // SpringApplication.run(SegoMarketNewApplication.class, args);
    }

}
