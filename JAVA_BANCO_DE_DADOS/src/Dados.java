import java.util.Scanner;

    public class Dados {
        public static void main(String[] args) throws Exception {

            Scanner input = new Scanner(System.in); // Criando um scanner para entrada do usuário

            DadosPessoais cadastro = new DadosPessoais();

            System.out.print("Nome: ");
            cadastro.Nome = input.nextLine(); // Lendo uma string do usuário

            System.out.print("Digite a folha: ");        
            cadastro.Nome_Genitor = input.nextLine(); // Lendo uma string do usuário

            System.out.print("Digite o termo: ");        
            cadastro.Nome_Genitora = input.nextLine(); // Lendo uma string do usuário


            //System.out.println("Livro registrado: " + cadastro.Livro);

            input.close(); // Fechando o scanner
        }
    }
