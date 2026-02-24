package com.example.biblioteca.service;

import com.example.biblioteca.dto.livro.LivroRequisicaoDto;
import com.example.biblioteca.dto.livro.LivroRespostaDto;
import com.example.biblioteca.mapper.LivroMapper;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class LivroService {

    private final LivroMapper livroMapper;

    private LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository, LivroMapper livroMapper){
        this.livroRepository=livroRepository;
        this.livroMapper=livroMapper;
    }


    public LivroRespostaDto salvar(LivroRequisicaoDto livroRequisicaoDto)throws SQLException{
        Livro livro = livroMapper.paraEntidade(livroRequisicaoDto);
        livroRepository.salvar(livro);
        return livroMapper.paraRespostaDto(livro);
    }

    public List<Livro> buscarTodos()throws SQLException{
        return livroRepository.buscarTodos();
    }

    public Livro buscarPorId(long id)throws SQLException{
        return livroRepository.buscarPorId(id);
    }

    public Livro atualizar(Livro livro, long id)throws SQLException{
        return livroRepository.atualizar(livro, id);
    }

    public void deletar(long id)throws SQLException{
        livroRepository.deletar(id);
    }


}
