package com.example.biblioteca.repository;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoRepository {


    public Emprestimo salvar(Emprestimo emprestimo) throws SQLException {
        String query = """
            INSERT INTO emprestimo
            (livro_id, usuario_id, data_emprestimo)
            VALUES
            (?,?,?)
            """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, emprestimo.getLivro_id());
            stmt.setLong(2, emprestimo.getUsuario_id());

            LocalDate dataParaGravar = (emprestimo.getDataEmprestimo() != null)
                    ? emprestimo.getDataEmprestimo()
                    : LocalDate.now();

            stmt.setDate(3, Date.valueOf(dataParaGravar));

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                emprestimo.setId(rs.getLong(1));
            }
        }
        return emprestimo;
    }

    public List<Emprestimo> buscarTodos()throws SQLException{
        List<Emprestimo> emprestimos = new ArrayList<>();

        String query = """
                SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao
                FROM emprestimo 
                """;
        try (Connection conn = Conexao.conectar();){
            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                long id = rs.getLong("id");
                long livroId = rs.getLong("livro_id");
                long usuarioId = rs.getLong("usuario_id");
                LocalDate dataEmprestimo = rs.getDate("data_emprestimo").toLocalDate();
                LocalDate dataDevolucao = rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null;
                emprestimos.add(new Emprestimo(id, livroId, usuarioId, dataEmprestimo, dataDevolucao));
            }
        }
        return emprestimos;
    }

    public Emprestimo buscarPorId(long id)throws SQLException {
        String query = """
                SELECT id, livro_id, usuario_id, data_emprestimo, data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new Emprestimo(
                        rs.getLong("id"),
                        rs.getLong("livro_id"),
                        rs.getLong("usuario_id"),
                        rs.getDate("data_emprestimo").toLocalDate(),
                        rs.getDate("data_devolucao") != null ? rs.getDate("data_devolucao").toLocalDate() : null
                );}
        }
        return null;
    }

    public Emprestimo atualizar(Emprestimo emprestimo, long id)throws SQLException{
        String query = """
                UPDATE emprestimo
                SET livro_id =?, usuario_id= ?, data_emprestimo= ?, data_devolucao=?
                WHERE id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, emprestimo.getLivro_id());
            stmt.setLong(2, emprestimo.getUsuario_id());
            LocalDate dataEmp = (emprestimo.getDataEmprestimo() != null) ?
                    emprestimo.getDataEmprestimo() : LocalDate.now();
            stmt.setDate(3, java.sql.Date.valueOf(dataEmp));

            if (emprestimo.getDataDevolucao() != null) {
                stmt.setDate(4, java.sql.Date.valueOf(emprestimo.getDataDevolucao()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            stmt.setLong(5, id);
            stmt.executeUpdate();
        }
        return emprestimo;
    }

    public void deletar(long id)throws SQLException{
        String query = """
                DELETE FROM emprestimo
                WHERE id =?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public void registrarDevolucao(long id) throws SQLException {
        String query = """
            UPDATE emprestimo
            SET data_devolucao = ?
            WHERE id = ?
            """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));

            stmt.setLong(2, id);

            stmt.executeUpdate();
        }
    }

}
