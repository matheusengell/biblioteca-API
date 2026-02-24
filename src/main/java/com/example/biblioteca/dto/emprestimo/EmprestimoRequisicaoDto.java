package com.example.biblioteca.dto.emprestimo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record EmprestimoRequisicaoDto(
   long livro_id,
   long usuario_id,
   LocalDate dataEmprestimo,
   LocalDate dataDevolucao
) {
}
