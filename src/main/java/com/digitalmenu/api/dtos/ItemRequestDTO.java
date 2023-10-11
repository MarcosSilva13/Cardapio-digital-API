package com.digitalmenu.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRequestDTO(
        @NotBlank(message = "Campo nome não pode ser vazio ou nulo.")
        String name,

        @NotNull(message = "Campo preço não pode ser nulo.")
        Double price,

        @NotBlank(message = "Campo imagem não pode ser vazio ou nulo.")
        String image,

        @NotBlank(message = "Campo ingredientes não pode ser vazio ou nulo.")
        String ingredients,

        @NotBlank(message = "Campo categoria não pode ser vazio ou nulo.")
        String categoryId) {

}
