package com.example.biblioteca.controller;

import com.example.biblioteca.dto.usuario.UsuarioRequisicaoDto;
import com.example.biblioteca.dto.usuario.UsuarioRespostaDto;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bibliotecaUser")
@Validated
public class UsuarioController {

    private UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @PostMapping
    public UsuarioRespostaDto cadastrarUsuario(
           @Valid @RequestBody UsuarioRequisicaoDto usuarioRequisicaoDto
            ){
        try {
            return usuarioService.salvar(usuarioRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<UsuarioRespostaDto> listarTodos(){
        try {
            return usuarioService.buscarTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping ("/{id}")
    public UsuarioRespostaDto buscarPorId(
            @PathVariable @Positive(message = "O id deve ser positivo") long id
    ){
        try {
            return usuarioService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping ("/{id}")
    public UsuarioRespostaDto atualizar(
            @Valid  @RequestBody UsuarioRequisicaoDto usuarioRequisicaoDto,
            @PathVariable @Positive(message = "O id deve ser positivo") long id
    ){
        try {
            return usuarioService.atualizar(usuarioRequisicaoDto, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void remover(
            @PathVariable @Positive(message = "O id deve ser positivo") long id
    ){
        try {
             usuarioService.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
