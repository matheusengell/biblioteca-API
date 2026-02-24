package com.example.biblioteca.controller;

import com.example.biblioteca.dto.emprestimo.EmprestimoRequisicaoDto;
import com.example.biblioteca.dto.emprestimo.EmprestimoRespostaDto;
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
    public EmprestimoRespostaDto cadastrarEmprestimo(
            @RequestBody EmprestimoRequisicaoDto emprestimoRequisicaoDto
    ){
        try {
            return emprestimoService.salvar(emprestimoRequisicaoDto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<EmprestimoRespostaDto> listarTodos(){
        try {
            return emprestimoService.buscarTodos();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping ("/{id}")
    public EmprestimoRespostaDto listarPorId(
            @PathVariable long id
    ){
        try {
            return emprestimoService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public EmprestimoRespostaDto atualizar(
            @RequestBody EmprestimoRequisicaoDto emprestimoRequisicaoDto,
            @PathVariable long id
    ){
        try {
            return emprestimoService.atualizar(emprestimoRequisicaoDto, id);
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
