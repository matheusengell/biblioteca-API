package com.example.biblioteca.service;

import com.example.biblioteca.dto.livro.LivroRequisicaoDto;
import com.example.biblioteca.dto.livro.LivroRespostaDto;
import com.example.biblioteca.mapper.LivroMapper;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public List<LivroRespostaDto> buscarTodos()throws SQLException{
        List<Livro> livros = livroRepository.buscarTodos();
        List<LivroRespostaDto> livroRespostaDtos = new ArrayList<>();

        for(Livro l: livros){
            livroRespostaDtos.add(livroMapper.paraRespostaDto(l));
        }
        return livroRespostaDtos;
    }

    public LivroRespostaDto buscarPorId(long id)throws SQLException{
        Livro livro = livroRepository.buscarPorId(id);
        return livroMapper.paraRespostaDto(livro);
    }

    public LivroRespostaDto atualizar(LivroRequisicaoDto livroRequisicaoDto, long id)throws SQLException{
        Livro livro = livroMapper.paraEntidade(livroRequisicaoDto);
        livroRepository.atualizar(livro,id);
        return livroMapper.paraRespostaDto(livro);
    }

    public void deletar(long id)throws SQLException{
        livroRepository.deletar(id);
    }


}
