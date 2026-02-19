package com.example.biblioteca.controller;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/bibliotecaUser")
public class UsuarioController {

    private UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @PostMapping
    public Usuario cadastrarUsuario(
            @RequestBody Usuario usuario
    ){
        try {
            return usuarioService.salvar(usuario);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Usuario> listarTodos(
            @RequestBody Usuario usuario
    ){
        try {
            return usuarioService.buscarTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping ("/{id}")
    public Usuario buscarPorId(
            @PathVariable long id
    ){
        try {
            return usuarioService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping ("/{id}")
    public Usuario atualizar(
            @RequestBody Usuario usuario,
            @PathVariable long id
    ){
        try {
            return usuarioService.atualizar(usuario, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public void remover(
            @PathVariable long id
    ){
        try {
             usuarioService.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
