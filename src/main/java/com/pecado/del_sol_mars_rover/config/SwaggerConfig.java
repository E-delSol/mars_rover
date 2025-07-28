package com.pecado.del_sol_mars_rover.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    /**
     * This method creates and returns the OpenAPI configuration.
     * It includes the API title, description, version, contact and license info.
     *
     * @return OpenAPI object with documentation settings
     */
    @Bean
    public OpenAPI marsRoverOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mars Rover API")
                        .description("This API lets you manage rovers, maps and obstacles on Mars.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your@email.com"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project GitHub Repository")
                        .url("https://github.com/E-delSol/mars_rover"));
    }
}
