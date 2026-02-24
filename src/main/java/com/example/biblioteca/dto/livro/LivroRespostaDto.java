package com.example.biblioteca.dto.livro;

public record LivroRespostaDto(
        long id,
        String titulo,
        String autor,
        int ano_publicacao
) {
}
