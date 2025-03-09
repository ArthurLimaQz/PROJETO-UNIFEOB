package Models;

import java.time.LocalDate;

public class Registro extends Pessoa {
    private int termo;
    private String livro;
    private int folha;
    private LocalDate dataRegistro;
    private String nomeGenitor;
    private String nomeGenitora;

    public Registro(int termo, String livro, int folha, LocalDate dataRegistro, 
                    String nome, String nomeGenitor, String nomeGenitora, 
                    LocalDate dataNascimento, String sexo) {
        super(nome, dataNascimento, sexo);
        this.termo = termo;
        this.livro = livro;
        this.folha = folha;
        this.dataRegistro = dataRegistro;
        this.nomeGenitor = nomeGenitor;
        this.nomeGenitora = nomeGenitora;
    }

    // Getters e Setters
    public int getTermo() {
        return termo;
    }

    public String getLivro() {
        return livro;
    }

    public int getFolha() {
        return folha;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public String getNomeGenitor() {
        return nomeGenitor;
    }

    public String getNomeGenitora() {
        return nomeGenitora;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Registro: " + termo + " | Nome: " + nome + " | Data Nasc: " + dataNascimento);
    }
}
