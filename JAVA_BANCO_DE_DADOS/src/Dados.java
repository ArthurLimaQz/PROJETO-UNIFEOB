import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


    public class Dados {
        public static void main(String[] args) throws Exception {

            Scanner input = new Scanner(System.in); // Criando um scanner para entrada do usuário
            DadosPessoais cadastro = new DadosPessoais();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato da data
            
            System.out.println("Nome: ");
            cadastro.Nome = input.nextLine(); // Lendo uma string do usuário

            System.out.print("Digite o nome do genitor: ");        
            cadastro.Nome_Genitor = input.nextLine(); // Lendo uma string do usuário

            System.out.print("Digite o nome da Genitora: ");        
            cadastro.Nome_Genitora = input.nextLine(); // Lendo uma string do usuário

            System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
            String dataString = input.nextLine();
            cadastro.Data_Nascimento = LocalDate.parse(dataString, formatter); // Converte para LocalDate
    
            System.out.println("Seu nome é " + cadastro.Nome);
            System.out.println("Seu pai é " + cadastro.Nome_Genitor);
            System.out.println("Sua mãe é " + cadastro.Nome_Genitora);
            System.out.println("Sua data de nascimento é " + cadastro.Data_Nascimento);            

            input.close(); // Fechando o scanner
        }
    }
