package Models;

import java.time.LocalDate;

public class Registro {
    private int Termo;
    private String Livro;
    private int Folha;
    private LocalDate Data_registro;
    private String Nome;
    private String Nome_genitor;
    private String Nome_genitora;
    private LocalDate Data_nascimento;
    private String Sexo;

    public Registro(int Termo, String Livro, int Folha, LocalDate Data_registro, String Nome, 
                    String Nome_genitor, String Nome_genitora, LocalDate Data_nascimento, String Sexo) {
        this.Termo = Termo;
        this.Livro = Livro;
        this.Folha = Folha;
        this.Data_registro = Data_registro;
        this.Nome = Nome;
        this.Nome_genitor = Nome_genitor;
        this.Nome_genitora = Nome_genitora;
        this.Data_nascimento = Data_nascimento;
        this.Sexo = Sexo;
    }

    public Registro() {
        // Construtor vazio necessário para evitar erro
    }

    // Getters e Setters
    public int getTermo() {
        return Termo;
    }

    public void setTermo(int Termo) {
        this.Termo = Termo;
    }

    public String getLivro() {
        return Livro;
    }

    public void setLivro(String Livro) {
        this.Livro = Livro;
    }

    public int getFolha() {
        return Folha;
    }

    public void setFolha(int Folha) {
        this.Folha = Folha;
    }

    public LocalDate getData_registro() {
        return Data_registro;
    }

    public void setData_registro(LocalDate Data_registro) {
        this.Data_registro = Data_registro;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getNome_genitor() {
        return Nome_genitor;
    }

    public void setNome_genitor(String Nome_genitor) {
        this.Nome_genitor = Nome_genitor;
    }

    public String getNome_genitora() {
        return Nome_genitora;
    }

    public void setNome_genitora(String Nome_genitora) {
        this.Nome_genitora = Nome_genitora;
    }

    public LocalDate getData_nascimento() {
        return Data_nascimento;
    }

    public void setData_nascimento(LocalDate Data_nascimento) {
        this.Data_nascimento = Data_nascimento;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }
}
