package com.digitalmenu.digitalmenuapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DigitalMenuRequestDTO(
        @NotBlank(message = "Campo nome não pode ser vazio ou nulo.")
        String name,

        @NotNull(message = "Campo preço não pode ser nulo.")
        Double price,

        @NotBlank(message = "Campo imagem não pode ser vazio ou nulo.")
        String image,

        @NotBlank(message = "Campo ingredientes não pode ser vazio ou nulo.")
        String ingredients) {

}
