package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Conexao;
import Models.Registro;
import Models.Pessoa;

public class RegistroDao {
    private Connection connection = Conexao.getconexao();

    public void save(Pessoa pessoa) {
        if (pessoa instanceof Registro) {
            Registro registro = (Registro) pessoa;
            String sql = "INSERT INTO registro (termo, livro, folha, data_registro, nome, nome_genitor, nome_genitora, data_nascimento, sexo) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, registro.getTermo());
                ps.setString(2, registro.getLivro());
                ps.setInt(3, registro.getFolha());
                ps.setDate(4, java.sql.Date.valueOf(registro.getDataRegistro()));
                ps.setString(5, registro.getNome());
                ps.setString(6, registro.getNomeGenitor());
                ps.setString(7, registro.getNomeGenitora());
                ps.setDate(8, java.sql.Date.valueOf(registro.getDataNascimento()));
                ps.setString(9, registro.getSexo());

                ps.executeUpdate();
                System.out.println("✅ Registro salvo com sucesso!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("❌ Tipo de pessoa não suportado para salvar no banco.");
        }
    }
    public List<Registro> getAll() {
    List<Registro> registros = new ArrayList<>();
    String sql = "SELECT * FROM registro";

    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Registro registro = new Registro(
                rs.getInt("termo"),
                rs.getString("livro"),
                rs.getInt("folha"),
                rs.getDate("data_registro").toLocalDate(),
                rs.getString("nome"),
                rs.getString("nome_genitor"),
                rs.getString("nome_genitora"),
                rs.getDate("data_nascimento").toLocalDate(),
                rs.getString("sexo")
            );
            registros.add(registro);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return registros;
    }
    public void delete(int termo) {
        String sql = "DELETE FROM registro WHERE termo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, termo);
            ps.executeUpdate();
            System.out.println("✅ Registro excluído com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
