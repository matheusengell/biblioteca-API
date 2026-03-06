package com.example.biblioteca.controller;


import com.example.biblioteca.dto.livro.LivroRequisicaoDto;
import com.example.biblioteca.dto.livro.LivroRespostaDto;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/biblioteca")
@Validated
public class LivroController {


    private LivroService livroService;
    public LivroController(LivroService livroService)throws SQLException{
        this.livroService=livroService;
    }

    @PostMapping
    public LivroRespostaDto cadastrarLivro(
            @RequestBody @Valid LivroRequisicaoDto livroRequisicaoDto) {
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
            @PathVariable @Positive(message = "O id deve ser positivo") long id
    ){
        try {
            return livroService.buscarPorId(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public LivroRespostaDto atualizar(
            @PathVariable @Positive(message = "O id deve ser positivo") long id,
            @RequestBody @Valid LivroRequisicaoDto livroRequisicaoDto
    ){
        try {
            return livroService.atualizar(livroRequisicaoDto, id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void remover(
            @PathVariable @Positive(message = "O id deve ser positivo") long id
    ){
        try {
             livroService.deletar(id);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
