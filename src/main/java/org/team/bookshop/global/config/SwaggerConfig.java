package org.team.bookshop.global.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    private final String REST_API_ROOT = "/api/v1/**";
    private final String REST_API_GROUP = "REST API";

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("온라인 서점 API")
                        .description("3팀에서 제작한 서점 API 명세서입니다.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

    @Bean
    public GroupedOpenApi restApi(){

        return GroupedOpenApi.builder()
                .pathsToMatch(REST_API_ROOT)
                .group(REST_API_GROUP)
                .build();
    }


}
