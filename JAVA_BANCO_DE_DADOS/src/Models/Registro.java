package Models;

import java.time.LocalDate;

public class Registro extends Pessoa {
    private int termo;
    private String livro;
    private int folha;
    private LocalDate dataRegistro;
 

    public Registro(int termo, String livro, int folha, LocalDate dataRegistro, 
        String nome, String nomeGenitor, String nomeGenitora, 
        LocalDate dataNascimento, String sexo) {
        super(0, nome, dataNascimento, sexo, nomeGenitor, nomeGenitora); // Definindo Id como 0 caso não seja informado
        this.termo = termo;
        this.livro = livro;
        this.folha = folha;
        this.dataRegistro = dataRegistro;
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

    @Override
    public void exibirInformacoes() {
        System.out.println("Registro: " + termo + " | Nome: " + nome + " | Data Nasc: " + dataNascimento);
    }
}
