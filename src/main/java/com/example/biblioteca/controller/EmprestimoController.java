package com.example.biblioteca.controller;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.service.EmprestimoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping ("/bibliotecaEmprestimo")
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService)throws SQLException{
        this.emprestimoService=emprestimoService;
    }

    @PostMapping
    public Emprestimo cadastrarEmprestimo(
            @RequestBody Emprestimo emprestimo
    ){
        try {
            return emprestimoService.salvar(emprestimo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<Emprestimo> listarTodos(){
        try {
            return emprestimoService.buscarTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping ("/{id}")
    public Emprestimo listarPorId(
            @PathVariable long id
    ){
        try {
            return emprestimoService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public Emprestimo atualizar(
            @RequestBody Emprestimo emprestimo,
            @PathVariable long id
    ){
        try {
            return emprestimoService.atualizar(emprestimo, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping ("/{id}")
    public void remover(
            @PathVariable long id
    ){
        try {
            emprestimoService.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}/devolucao")
    public void registrarDevolucao(
            @PathVariable long id
    ){
        try {
            emprestimoService.registrarDevolucao(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
