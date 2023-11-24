package com.pantrypalbackend.pantrypalbackend.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("PantryPal API")
                        .version("1.0.0")
                        .description("""
                                This is the API documentation for the PantryPal Application Back-end.
                                To get in touch with the developer, find the contact info below:""")
                        .contact(new Contact().name("Bright Dikko").email("BDikko18@gmail.com")));
    }
}
