package com.example.biblioteca.repository;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroRepository {

    public Livro salvar(Livro livro)throws SQLException{
        String query = """
                INSERT INTO livro
                (titulo, autor, ano_publicacao)
                VALUES
                (?,?,?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setLong(3, livro.getAno_publicacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                livro.setId(rs.getLong(1));
            }
        }
        return livro;
    }

    public List<Livro> buscarTodos()throws SQLException{
        List<Livro> livros = new ArrayList<>();

        String query = """
                SELECT id, titulo, autor, ano_publicacao
                FROM livro 
                """;
        try (Connection conn = Conexao.conectar();){
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano = rs.getInt("ano_publicacao");
                livros.add(new Livro(id, titulo, autor, ano));
            }
        }
        return livros;
    }

    public Livro buscarPorId(long id)throws SQLException {
        String query = """
                SELECT id, titulo, autor, ano_publicacao
                FROM livro
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new Livro(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getInt("ano_publicacao")
            );}
        }
        return null;
    }

    public Livro atualizar(Livro livro, long id)throws SQLException{
        String query = """
                UPDATE livro
                SET titulo = ?, autor = ?, ano_publicacao = ?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno_publicacao());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        }
        return livro;
    }

    public void deletar(long id)throws SQLException{
        String query = """
                DELETE FROM livro
                WHERE id =?
                """;
        try (Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
