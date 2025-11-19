package com.valtxcorresponsal.retiro_service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.valtxcorresponsal.retiro_service.business.consume")
public class RetiroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetiroServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Cajero Corresponsal - Retiros")
						.version("1.0.0")
						.description("Cajero Corresponsal - Retiros")
						.termsOfService("http://www.orderslab.io/terms/"));
	}
}
