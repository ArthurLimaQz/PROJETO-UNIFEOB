package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Conexao;
import Models.Registro;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class RegistroDao {
    private Connection connection = Conexao.getconexao();

    public void save(Registro registro) {
        String sql = "INSERT INTO registro (termo, livro, folha, data_registro, nome, nome_genitor, nome_genitora, data_nascimento, sexo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, registro.getTermo());
            ps.setString(2, registro.getLivro());
            ps.setInt(3, registro.getFolha());
            ps.setDate(4, java.sql.Date.valueOf(registro.getData_registro()));
            ps.setString(5, registro.getNome());
            ps.setString(6, registro.getNome_genitor());
            ps.setString(7, registro.getNome_genitora());
            ps.setDate(8, java.sql.Date.valueOf(registro.getData_nascimento()));
            ps.setString(9, registro.getSexo());

            ps.executeUpdate();
            System.out.println("✅ Registro salvo com sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Registro registro) {
        String sql = "UPDATE registro SET livro=?, folha=?, data_registro=?, nome=?, nome_genitor=?, " +
                     "nome_genitora=?, data_nascimento=?, sexo=? WHERE termo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, registro.getLivro());
            ps.setInt(2, registro.getFolha());
            ps.setDate(3, java.sql.Date.valueOf(registro.getData_registro()));
            ps.setString(4, registro.getNome());
            ps.setString(5, registro.getNome_genitor());
            ps.setString(6, registro.getNome_genitora());
            ps.setDate(7, java.sql.Date.valueOf(registro.getData_nascimento()));
            ps.setString(8, registro.getSexo());
            ps.setInt(9, registro.getTermo()); // TERMO agora é a chave primária

            ps.executeUpdate();
            System.out.println("✅ Cadastro atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveOrUpdate(Registro registro) {
        if (exists(registro.getTermo())) {
            update(registro);
        } else {
            save(registro);
        }
    }

    public void delete(int termo) {
        String sql = "DELETE FROM registro WHERE termo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, termo);
            ps.executeUpdate();
            System.out.println("✅ Registro deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Registro> getAll() {
        List<Registro> registros = new ArrayList<>();
        String sql = "SELECT * FROM registro";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Registro registro = new Registro();
                registro.setTermo(rs.getInt("termo"));
                registro.setLivro(rs.getString("livro"));
                registro.setFolha(rs.getInt("folha"));
                registro.setData_registro(rs.getDate("data_registro").toLocalDate());
                registro.setNome(rs.getString("nome"));
                registro.setNome_genitor(rs.getString("nome_genitor"));
                registro.setNome_genitora(rs.getString("nome_genitora"));
                registro.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
                registro.setSexo(rs.getString("sexo"));

                registros.add(registro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registros;
    }

    public boolean exists(int termo) {
        String sql = "SELECT COUNT(*) FROM registro WHERE termo=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, termo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
