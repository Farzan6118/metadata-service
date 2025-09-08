package com.nesstiff.reference.metadataservice.config;


//@Configuration
//@ConditionalOnExpression(value = "${useSwagger}")
//
//public class SwaggerConfig {
//    @Value("${gateway.address}")
//    private String gatewayAddress;
//    @Value("${spring.application.name}")
//    private String applicationName;
//    @Value("${use-application-in-local}")
//    private Boolean useLocalApplication;
//    @Value("${server.port}")
//    private Integer serverPort;
//
//
//    @Bean
//    public OpenAPI openAPI() {
//        List<Server> servers = new ArrayList<>();
//        Server server = new Server();
//        if (!useLocalApplication) {
//            server.setUrl(String.format("%s/%s", gatewayAddress, applicationName));
//        } else {
//            Server secondServer = new Server();
//            secondServer.setUrl(String.format("%s/%s", gatewayAddress, applicationName));
//            server.setUrl(String.format("http://localhost:%d", serverPort));
//            servers.add(secondServer);
//        }
//        servers.add(server);
//        return new OpenAPI().servers(servers).addSecurityItem(new SecurityRequirement().
//                        addList("Bearer Authentication"))
//                .components(new Components().addSecuritySchemes
//                        ("Bearer Authentication", createAPIKeyScheme()))
//                .info(new Info().title("")
//                        .description("")
//                        .version("1.0").contact(new Contact().name("002 team")
//                                .email("www.momtaz.ir").url("support@momtaz.ir"))
//                        .license(new License().name("002 team")
//                                .url("https://002.com")));
//    }
//
//    private SecurityScheme createAPIKeyScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }
//}
