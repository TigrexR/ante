package com.tigrex.loki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaLokiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaLokiApplication.class, args);
    }

}
