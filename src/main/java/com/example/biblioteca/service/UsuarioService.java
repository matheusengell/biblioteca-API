package com.example.biblioteca.service;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository)throws SQLException{
        this.usuarioRepository=usuarioRepository;
    }

    public Usuario salvar(Usuario usuario)throws SQLException{
        return usuarioRepository.salvar(usuario);
    }

    public List<Usuario> buscarTodos()throws SQLException{
        return usuarioRepository.buscarTodos();
    }

    public Usuario buscarPorId(long id)throws SQLException{
        return usuarioRepository.buscarPorId(id);
    }

    public Usuario atualizar(Usuario usuario, long id)throws SQLException{
        return usuarioRepository.atualizar(usuario, id);
    }

    public void deletar(long id)throws SQLException{
        usuarioRepository.deletar(id);
    }

}
