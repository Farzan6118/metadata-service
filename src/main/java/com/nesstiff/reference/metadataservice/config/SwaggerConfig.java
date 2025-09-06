package com.nesstiff.reference.metadataservice.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
@ConditionalOnExpression(value = "${useSwagger}")

public class SwaggerConfig {
    @Value("${gateway.address}")
    private String gatewayAddress;
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${use-application-in-local}")
    private Boolean useLocalApplication;
    @Value("${server.port}")
    private Integer serverPort;


    @Bean
    public OpenAPI openAPI() {
        List<Server> servers = new ArrayList<>();
        Server server = new Server();
        if (!useLocalApplication) {
            server.setUrl(String.format("%s/%s", gatewayAddress, applicationName));
        } else {
            Server secondServer = new Server();
            secondServer.setUrl(String.format("%s/%s", gatewayAddress, applicationName));
            server.setUrl(String.format("http://localhost:%d", serverPort));
            servers.add(secondServer);
        }
        servers.add(server);
        return new OpenAPI().servers(servers).addSecurityItem(new SecurityRequirement().
                        addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes
                        ("Bearer Authentication", createAPIKeyScheme()))
                .info(new Info().title("")
                        .description("")
                        .version("1.0").contact(new Contact().name("002 team")
                                .email("www.momtaz.ir").url("support@momtaz.ir"))
                        .license(new License().name("002 team")
                                .url("https://002.com")));
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
