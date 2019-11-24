package com.dementei.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class CatalogApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CatalogApplication.class, args);
    }
}
