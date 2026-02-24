package com.example.biblioteca.mapper;

import com.example.biblioteca.dto.livro.LivroRequisicaoDto;
import com.example.biblioteca.dto.livro.LivroRespostaDto;
import com.example.biblioteca.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

public Livro paraEntidade(
    LivroRequisicaoDto livroRequisicaoDto
){
    return new Livro(
            livroRequisicaoDto.titulo(),
            livroRequisicaoDto.autor(),
            livroRequisicaoDto.ano_publicacao()
    );
}

public LivroRespostaDto paraRespostaDto(
        Livro livro
){
    return new LivroRespostaDto(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getAno_publicacao()
    );
}
}
