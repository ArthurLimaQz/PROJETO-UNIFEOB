package Controller;
import Models.Registro;
import Dao.RegistroDao;
import java.time.LocalDate;

public class RegistroController {
    private RegistroDao registroDao = new RegistroDao(); // DAO para acesso ao banco de dados

    public void registrar(int termo, String livro, int folha, String nome, 
                            String nomeGenitor, String nomeGenitora, LocalDate dataNascimento, 
                            String sexo, LocalDate dataRegistro) {
    
            Registro novoRegistro = new Registro(termo, livro, folha, dataRegistro, nome, nomeGenitor, 
                nomeGenitora, dataNascimento, sexo);

        registroDao.save(novoRegistro); // Salvar o novo registro no banco de dados
    }
}




