package com.example.springkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableConfigurationProperties
public class SpringKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaApplication.class, args);
    }

}
