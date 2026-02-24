package com.example.biblioteca.controller;


import com.example.biblioteca.dto.livro.LivroRequisicaoDto;
import com.example.biblioteca.dto.livro.LivroRespostaDto;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.service.LivroService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class LivroController {


    private LivroService livroService;
    public LivroController(LivroService livroService)throws SQLException{
        this.livroService=livroService;
    }

    @PostMapping
    public LivroRespostaDto cadastrarLivro(
            @RequestBody LivroRequisicaoDto livroRequisicaoDto) {
        try {
            return livroService.salvar(livroRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<LivroRespostaDto>listarTodos(
    ){
        try {
            return livroService.buscarTodos();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public LivroRespostaDto buscarPorId(
            @PathVariable long id
    ){
        try {
            return livroService.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public LivroRespostaDto atualizar(
            @PathVariable long id,
            @RequestBody LivroRequisicaoDto livroRequisicaoDto
    ){
        try {
            return livroService.atualizar(livroRequisicaoDto, id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void remover(
            @PathVariable long id
    ){
        try {
             livroService.deletar(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
