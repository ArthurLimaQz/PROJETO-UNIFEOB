package Models;

import java.time.LocalDate;

public abstract class Pessoa {
    protected int Id;
    protected String nome;
    protected LocalDate dataNascimento;
    protected String sexo;
    protected String nomeGenitor;
    protected String nomeGenitora;

    public Pessoa(int Id, String nome, LocalDate dataNascimento, String sexo, String nomeGenitor, String nomeGenitora) {
        this.Id = Id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nomeGenitor = nomeGenitor;
        this.nomeGenitora = nomeGenitora;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomeGenitor() { // Nome corrigido
        return nomeGenitor;
    }
    
    public void setNomeGenitor(String nomeGenitor) { // Nome corrigido
        this.nomeGenitor = nomeGenitor;
    }
    
    public String getNomeGenitora() { // Nome corrigido
        return nomeGenitora;
    }
    
    public void setNomeGenitora(String nomeGenitora) { // Nome corrigido
        this.nomeGenitora = nomeGenitora;
    }

    public abstract void exibirInformacoes(); // Método abstrato
}
