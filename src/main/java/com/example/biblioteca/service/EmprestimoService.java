package com.example.biblioteca.service;


import com.example.biblioteca.dto.emprestimo.EmprestimoRequisicaoDto;
import com.example.biblioteca.dto.emprestimo.EmprestimoRespostaDto;
import com.example.biblioteca.mapper.EmprestimoMapper;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmprestimoService {

    private EmprestimoMapper emprestimoMapper;

    private EmprestimoRepository emprestimoRepository;
    public EmprestimoService(EmprestimoRepository emprestimoRepository , EmprestimoMapper emprestimoMapper)throws SQLException{
        this.emprestimoRepository=emprestimoRepository;
        this.emprestimoMapper=emprestimoMapper;
    }

    public EmprestimoRespostaDto salvar(EmprestimoRequisicaoDto emprestimoRequisicaoDto)throws SQLException{
        Emprestimo emprestimo = emprestimoMapper.paraEntidade(emprestimoRequisicaoDto);
        emprestimoRepository.salvar(emprestimo);
        return emprestimoMapper.paraDto(emprestimo);
    }

    public List<EmprestimoRespostaDto> buscarTodos()throws SQLException{
        List<Emprestimo> emprestimos = emprestimoRepository.buscarTodos();
        List<EmprestimoRespostaDto> respostaDtos = new ArrayList<>();
        for (Emprestimo e: emprestimos){
            respostaDtos.add(emprestimoMapper.paraDto(e));
        }
        return respostaDtos;
    }

    public EmprestimoRespostaDto buscarPorId(long id)throws SQLException{
        Emprestimo emprestimo = emprestimoRepository.buscarPorId(id);
        return emprestimoMapper.paraDto(emprestimo);
    }

    public EmprestimoRespostaDto atualizar(EmprestimoRequisicaoDto emprestimoRequisicaoDto, long id)throws SQLException{
        Emprestimo emprestimo = emprestimoMapper.paraEntidade(emprestimoRequisicaoDto);
        emprestimoRepository.atualizar(emprestimo, id);
        return emprestimoMapper.paraDto(emprestimo);
    }

    public void deletar(long id)throws SQLException{
        emprestimoRepository.deletar(id);
    }

    public void registrarDevolucao(long id)throws SQLException{
        emprestimoRepository.registrarDevolucao(id);
    }
}
