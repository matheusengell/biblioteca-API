package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.emprestimo.EmprestimoRequisicaoDto;
import com.example.biblioteca.dto.emprestimo.EmprestimoRespostaDto;
import com.example.biblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public Emprestimo paraEntidade(EmprestimoRequisicaoDto emprestimoRequisicaoDto){
        return new Emprestimo(
                emprestimoRequisicaoDto.livro_id(),
                emprestimoRequisicaoDto.usuario_id(),
                emprestimoRequisicaoDto.dataEmprestimo(),
                emprestimoRequisicaoDto.dataDevolucao()
        );
    }

    public EmprestimoRespostaDto paraDto(Emprestimo emprestimo){
        return new EmprestimoRespostaDto(
                emprestimo.getId(),
                emprestimo.getLivro_id(),
                emprestimo.getUsuario_id(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }
}
