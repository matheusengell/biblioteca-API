package com.example.biblioteca.dto.livro;

import jakarta.validation.constraints.*;

public record LivroRequisicaoDto(
        @NotBlank(message = "O título é obrigatório")
        @Size(min = 2, max = 255, message = "O título deve ter entre 2 e 255 caracteres")
    String titulo,

        @NotBlank(message = "O autor é obrigatório")
        @Size(min = 2, max = 150, message = "O título deve ter entre 2 e 150 caracteres")
    String autor,

        @Max( value = 2027, message = "O ano deve ser no passado")
        @Min(value = 1000, message = "O ano deve ser maior que 1000")
        @Positive (message = "O ano de publicação deve ser positivo")
    int ano_publicacao
) {
}
