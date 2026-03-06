package com.example.biblioteca.dto.emprestimo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record EmprestimoRequisicaoDto(
        @Positive(message = "O id do livro deve ser maior que 0")
        @NotNull(message = "O id do livro deve ser preenchido")
        Long livro_id,

        @Positive(message = "O id do usuário deve ser maior que 0")
        @NotNull(message = "O id do usuário deve ser preenchido")
        Long usuario_id,

        @NotNull(message = "A data de empréstimo é obrigatória")
        @Past(message = "A data deve ser no passado")
        LocalDate dataEmprestimo,

        @NotNull(message = "A data de devolução é obrigatória")
        @FutureOrPresent(message = "A data deve ser no futuro/presente")
        LocalDate dataDevolucao
) {
}
