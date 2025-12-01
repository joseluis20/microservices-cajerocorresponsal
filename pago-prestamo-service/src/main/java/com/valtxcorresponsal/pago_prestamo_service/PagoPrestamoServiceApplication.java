package com.valtxcorresponsal.pago_prestamo_service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = " com.valtxcorresponsal.pago_prestamo_service.business.domain.services")
@EnableFeignClients(
        basePackages = "com.valtxcorresponsal.pago_prestamo_service.business.consume"
)
public class PagoPrestamoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagoPrestamoServiceApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Cajero Corresponsal - Pago Prestamo")
						.version("1.0.0")
						.description("Cajero Corresponsal - Pago Prestamo..")
						.termsOfService("http://www.orderslab.io/terms/"));
	}
}
