import java.util.Scanner;


public class Matricula {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in); // Criando um scanner para entrada do usuário

        LivroRegistro cadastro = new LivroRegistro();

        System.out.print("Digite o livro: ");
        cadastro.Livro = input.nextLine(); // Lendo uma string do usuário

        System.out.print("Digite a folha: ");        
        cadastro.Folha = input.nextInt(); // Lendo uma string do usuário

        System.out.print("Digite o termo: ");        
        cadastro.Termo = input.nextInt(); // Lendo uma string do usuário


        //System.out.println("Livro registrado: " + cadastro.Livro);

        input.close(); // Fechando o scanner
    }
}
