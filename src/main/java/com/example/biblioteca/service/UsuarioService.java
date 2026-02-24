package com.example.biblioteca.service;

import com.example.biblioteca.dto.usuario.UsuarioRequisicaoDto;
import com.example.biblioteca.dto.usuario.UsuarioRespostaDto;
import com.example.biblioteca.mapper.UsuarioMapper;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioMapper usuarioMapper;

    private UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper)throws SQLException{
        this.usuarioRepository=usuarioRepository;
        this.usuarioMapper=usuarioMapper;
    }

    public UsuarioRespostaDto salvar(UsuarioRequisicaoDto usuarioRequisicaoDto)throws SQLException{
        Usuario usuario = usuarioMapper.paraEntidade(usuarioRequisicaoDto);
        usuarioRepository.salvar(usuario);
        return usuarioMapper.paraDto(usuario);
    }

    public List<UsuarioRespostaDto> buscarTodos()throws SQLException{
        List<Usuario> usuarios = usuarioRepository.buscarTodos();
        List<UsuarioRespostaDto> respostaDtos = new ArrayList<>();

        for(Usuario u : usuarios){
            respostaDtos.add(usuarioMapper.paraDto(u));
        }
        return respostaDtos;
    }

    public UsuarioRespostaDto buscarPorId(long id)throws SQLException{
        Usuario usuario = usuarioRepository.buscarPorId(id);
        return usuarioMapper.paraDto(usuario);
    }

    public UsuarioRespostaDto atualizar(UsuarioRequisicaoDto usuarioRequisicaoDto, long id)throws SQLException{
        Usuario usuario = usuarioMapper.paraEntidade(usuarioRequisicaoDto);
        usuarioRepository.atualizar(usuario, id);
        return usuarioMapper.paraDto(usuario);
    }

    public void deletar(long id)throws SQLException{
        usuarioRepository.deletar(id);
    }

}
