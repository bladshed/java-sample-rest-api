package com.bayzdelivery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@OpenAPIDefinition(
  info = @Info(
    title = "Sample Delivery API",
    description = "API definitions of Sample Delivery Microservice",
    version = "1.0.0"
))
public class BayzDeliveryApplication {
  public static void main(String[] args) {
    SpringApplication.run(BayzDeliveryApplication.class, args);
  }
}
