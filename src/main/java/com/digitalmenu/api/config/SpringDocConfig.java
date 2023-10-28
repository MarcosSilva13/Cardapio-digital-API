package com.digitalmenu.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cardápio digital API")
                        .version("v1")
                        .description("Api de um cardápio digital. A API permite criar um item, listar, atualizar e " +
                                "deletar. Permite criar uma categoria para os itens, listar, atualizar e deletar.")
                );
    }
}
