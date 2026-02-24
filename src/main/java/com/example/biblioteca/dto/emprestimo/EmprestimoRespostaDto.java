package com.example.biblioteca.dto.emprestimo;

import java.time.LocalDate;

public record EmprestimoRespostaDto(
        long id,
        long livro_id,
        long usuario_id,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
}
