package com.tigrex.kratos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaKratosApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaKratosApplication.class, args);
    }

}
