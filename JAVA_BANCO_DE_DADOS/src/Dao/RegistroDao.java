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
    
            String sqlPessoa = "INSERT INTO pessoa (nome, sexo, data_nascimento, nome_genitor, nome_genitora) VALUES (?, ?, ?, ?, ?)";
            String sqlRegistro = "INSERT INTO registro (Id, termo, livro, folha, data_registro) VALUES (?, ?, ?, ?, ?)";
    
            try {
                int pessoaId = -1;
                try (PreparedStatement psPessoa = connection.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    psPessoa.setString(1, registro.getNome());
                    psPessoa.setString(2, registro.getSexo());
                    psPessoa.setDate(3, java.sql.Date.valueOf(registro.getDataNascimento()));
                    psPessoa.setString(4, registro.getNomeGenitor());
                    psPessoa.setString(5, registro.getNomeGenitora());
                    psPessoa.executeUpdate();
    
                    // Recuperar o ID gerado automaticamente
                    try (ResultSet rs = psPessoa.getGeneratedKeys()) {
                        if (rs.next()) {
                            pessoaId = rs.getInt(1);
                        }
                    }
                }
    
                // Inserir na tabela REGISTRO usando o ID gerado
                if (pessoaId > 0) {
                    try (PreparedStatement psRegistro = connection.prepareStatement(sqlRegistro)) {
                        psRegistro.setInt(1, pessoaId); // Chave estrangeira
                        psRegistro.setInt(2, registro.getTermo());
                        psRegistro.setString(3, registro.getLivro());
                        psRegistro.setInt(4, registro.getFolha());
                        psRegistro.setDate(5, java.sql.Date.valueOf(registro.getDataRegistro()));
                        psRegistro.executeUpdate();
                    }
                }
    
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
        String sql = "SELECT r.termo, r.livro, r.folha, r.data_registro, " +
                     "p.nome, p.nome_genitor, p.nome_genitora, p.data_nascimento, p.sexo " +
                     "FROM registro r " +
                     "JOIN pessoa p ON r.Id = p.Id";
    
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
    
            while (rs.next()) {
                Registro registro = new Registro(
                    rs.getInt("termo"),
                    rs.getString("livro"),
                    rs.getInt("folha"),
                    rs.getDate("data_registro").toLocalDate(),
                    rs.getString("nome"),  // Agora a coluna existe no resultado
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
        String sqlGetId = "SELECT Id FROM registro WHERE termo=?";
        String sqlRegistro = "DELETE FROM registro WHERE termo=?";
        String sqlPessoa = "DELETE FROM pessoa WHERE Id=?";
    
        int pessoaId = -1;
    
        try {
            // Recuperar o ID da pessoa associado ao termo
            try (PreparedStatement psGetId = connection.prepareStatement(sqlGetId)) {
                psGetId.setInt(1, termo);
                try (ResultSet rs = psGetId.executeQuery()) {
                    if (rs.next()) {
                        pessoaId = rs.getInt("Id");
                    }
                }
            }
    
            // Se encontrou um ID válido, prossegue para excluir
            if (pessoaId > 0) {
                try (PreparedStatement psRegistro = connection.prepareStatement(sqlRegistro)) {
                    psRegistro.setInt(1, termo);
                    psRegistro.executeUpdate();
                }
    
                try (PreparedStatement psPessoa = connection.prepareStatement(sqlPessoa)) {
                    psPessoa.setInt(1, pessoaId);
                    psPessoa.executeUpdate();
                }
    
                System.out.println("✅ Registro e Pessoa excluídos com sucesso!");
            } else {
                System.out.println("❌ Nenhuma pessoa encontrada para o termo informado.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
}
