package com.example.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.time.LocalDate;

public class Emprestimo {

    private long id;
    private long livro_id;
    private long usuario_id;
    @JsonProperty("data_emprestimo")
    private LocalDate dataEmprestimo;
    @JsonProperty("data_devolucao")
    private LocalDate dataDevolucao;

    public Emprestimo(long id, long livro_id, long usuario_id, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livro_id = livro_id;
        this.usuario_id = usuario_id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(LocalDate dataDevolucao, LocalDate dataEmprestimo, long usuario_id, long livro_id) {
        this.dataDevolucao = dataDevolucao;
        this.dataEmprestimo = dataEmprestimo;
        this.usuario_id = usuario_id;
        this.livro_id = livro_id;
    }

    public Emprestimo(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(long livro_id) {
        this.livro_id = livro_id;
    }

    public long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(long usuario_id) {
        this.usuario_id = usuario_id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
