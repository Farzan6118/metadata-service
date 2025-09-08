package com.nesstiff.reference.metadataservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    public OpenAPI usersMicroserviceOpenAPI() {

        return new OpenAPI()

                .info(new Info().title("مدیریت کاربران")
                        .description("مدیریت ورود و خروج کاربران و اعطای دسترسی")
                        .version("1.0"));
    }

}
