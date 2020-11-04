package org.una.aeropuerto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication
@EnableSwagger2
public class AeropuertoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AeropuertoApplication.class, args);
    }
}