package com.example.biblioteca.controller;

import com.example.biblioteca.dto.emprestimo.EmprestimoRequisicaoDto;
import com.example.biblioteca.dto.emprestimo.EmprestimoRespostaDto;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping ("/bibliotecaEmprestimo")
@Validated
public class EmprestimoController {

    private EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService)throws SQLException{
        this.emprestimoService=emprestimoService;
    }

    @PostMapping
    public EmprestimoRespostaDto cadastrarEmprestimo(
            @RequestBody @Valid EmprestimoRequisicaoDto emprestimoRequisicaoDto
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
            @PathVariable @Positive(message = "O id deve ser positivo") long id
    ){
        try {
            return emprestimoService.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public EmprestimoRespostaDto atualizar(
            @RequestBody @Valid EmprestimoRequisicaoDto emprestimoRequisicaoDto,
            @PathVariable  @Positive(message = "O id deve ser positivo") long id
    ){
        try {
            return emprestimoService.atualizar(emprestimoRequisicaoDto  , id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping ("/{id}")
    public void remover(
            @PathVariable @Positive(message = "O id deve ser positivo") long id
    ){
        try {
            emprestimoService.deletar(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}/devolucao")
    public void registrarDevolucao(
            @PathVariable  @Positive(message = "O id deve ser positivo") long id
    ){
        try {
            emprestimoService.registrarDevolucao(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
