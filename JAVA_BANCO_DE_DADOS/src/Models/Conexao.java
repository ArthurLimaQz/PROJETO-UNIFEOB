package Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/unifeob"; // Substitua pelo nome correto do seu banco
    private static final String USUARIO = "root"; // Seu usuário do banco
    private static final String SENHA = "123456789"; // Sua senha do banco

    private static Connection conexao = null;

    public static Connection getconexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                System.out.println("Conexão estabelecida com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro ao conectar ao banco: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return conexao;
    }
}
