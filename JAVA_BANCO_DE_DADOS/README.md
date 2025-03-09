## Sistema de Registro com Java e MySQL

📋 Sobre o Projeto

Este projeto é um sistema de registro de cartório desenvolvido em Java utilizando Java Swing para a interface gráfica e MySQL<br>para armazenar os dados. Ele permite cadastrar, visualizar e excluir registros de forma intuitiva.

📌 Funcionalidades

✅ Cadastro de Registros: O usuário pode inserir dados como nome, termo, livro, data de nascimento, entre outros.
✅ Listagem de Registros: Os registros são carregados do banco e exibidos na interface gráfica.
✅ Exclusão de Registros: O usuário pode selecionar um registro e excluí-lo do banco de dados.

📌 Estrutura do Código

O projeto segue os princípios da Programação Orientada a Objetos (POO), aplicando Abstração, Herança, Polimorfismo e Encapsulamento.

📂 src/
 ├── 📂 Controller/
 │    ├── RegistroController.java
 ├── 📂 Dao/
 │    ├── RegistroDao.java
 ├── 📂 Models/
 │    ├── Pessoa.java
 │    ├── Registro.java
 │    ├── Conexao.java
 ├── 📂 View/
 │    ├── RegistroGUI.java
 │    ├── EntradaView.java

📌 Aplicação dos Conceitos de POO

1️⃣ Abstração
Definição: A abstração permite criar classes genéricas que modelam conceitos do mundo real, escondendo detalhes internos e expondo apenas funcionalidades essenciais.

📌 No código: Criamos a classe abstrata Pessoa, que define atributos comuns para qualquer pessoa (nome, data de nascimento, sexo). Essa classe serve como modelo para a classe Registro, garantindo que todas as pessoas cadastradas tenham essas características básicas.

public abstract class Pessoa {
    protected String nome;
    protected LocalDate dataNascimento;
    protected String sexo;

    public Pessoa(String nome, LocalDate dataNascimento, String sexo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public abstract void exibirInformacoes(); // Método abstrato
}

➡ Benefício: Permite que outras classes derivem dessa estrutura sem precisar duplicar código.

2️⃣ Herança
Definição: A herança permite que uma classe reutilize atributos e métodos de outra classe.

📌 No código: A classe Registro herda de Pessoa, aproveitando seus atributos e adicionando novas informações específicas, como termo, livro e datas.
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
        super(nome, dataNascimento, sexo); // Chama o construtor da superclasse (Pessoa)
        this.termo = termo;
        this.livro = livro;
        this.folha = folha;
        this.dataRegistro = dataRegistro;
        this.nomeGenitor = nomeGenitor;
        this.nomeGenitora = nomeGenitora;
    }
}
➡ Benefício: Evita duplicação de código e mantém uma estrutura organizada.

3️⃣ Polimorfismo
Definição: O polimorfismo permite que um mesmo método funcione de maneira diferente dependendo da classe que o implementa.

📌 No código:

A classe RegistroDao aceita qualquer objeto do tipo Pessoa no método save(), mas só insere no banco se for um Registro.
Isso permite futuras expansões, como o cadastro de outros tipos de pessoas (exemplo: Funcionário, Cliente, etc.).
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
    }
}
➡ Benefício: Aumenta a flexibilidade do código, permitindo reutilização e expansão.

4️⃣ Encapsulamento
Definição: O encapsulamento protege os dados dentro das classes, permitindo o acesso somente por meio de métodos específicos.

📌 No código:

Todos os atributos das classes são privados (private).
O acesso aos atributos é feito por métodos get e set, garantindo controle sobre os dados.
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
➡ Benefício: Impede alterações indesejadas nos dados e melhora a segurança do sistema.
📌 Como Executar o Projeto
🔹 Pré-requisitos
Java 8+
MySQL
Bibliotecas JDBC
IDE (IntelliJ, VS Code, NetBeans, Eclipse)
