package com.inditex.priceservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Price Service REST API Documentation",
				description = "Price Service REST API Documentation for product pricing queries",
				version = "v1",
				contact = @Contact(
						name = "David Franco García",
						email = "david.fragar@gmail.com",
						url = "https://github.com/dfragar"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://opensource.org/licenses/Apache-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Price Service API Documentation",
				url = "http://localhost:8080/swagger-ui/index.html"
		)
)
public class PriceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceServiceApplication.class, args);
	}

}
