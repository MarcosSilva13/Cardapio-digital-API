package com.digitalmenu.digitalmenuapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Campo nome não pode ser vazio ou nulo.")
        String name) {
}
