package com.example.biblioteca.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequisicaoDto (
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Email(message = "E-mail inválido!")
        @NotBlank(message = "O e-mail é obrigatório")
        String email
){
}
