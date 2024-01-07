package ru.abradox.yandexgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class YandexGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YandexGatewayApplication.class, args);
    }

}
