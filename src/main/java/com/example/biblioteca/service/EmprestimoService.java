package com.example.biblioteca.service;


import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.repository.EmprestimoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    public EmprestimoService(EmprestimoRepository emprestimoRepository)throws SQLException{
        this.emprestimoRepository=emprestimoRepository;
    }

    public Emprestimo salvar(Emprestimo emprestimo)throws SQLException{
        return emprestimoRepository.salvar(emprestimo);
    }

    public List<Emprestimo> buscarTodos()throws SQLException{
        return emprestimoRepository.buscarTodos();
    }

    public Emprestimo buscarPorId(long id)throws SQLException{
        return emprestimoRepository.buscarPorId(id);
    }

    public Emprestimo atualizar(Emprestimo emprestimo, long id)throws SQLException{
        return  emprestimoRepository.atualizar(emprestimo, id);
    }

    public void deletar(long id)throws SQLException{
        emprestimoRepository.deletar(id);
    }

    public void registrarDevolucao(long id)throws SQLException{
        emprestimoRepository.registrarDevolucao(id);
    }
}
