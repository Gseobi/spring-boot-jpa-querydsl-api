package com.github.gseobi.kpos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KposApplication {
    public static void main(String[] args) {
        SpringApplication.run(KposApplication.class, args);
    }
}
