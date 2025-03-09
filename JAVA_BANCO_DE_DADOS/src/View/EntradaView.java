package View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import Controller.RegistroController;

public class EntradaView {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RegistroController controller = new RegistroController();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Digite o termo: ");
        int termo = input.nextInt();
        input.nextLine(); 

        System.out.print("Digite o livro: ");
        String livro = input.nextLine();

        System.out.print("Digite o número da folha: ");
        int folha = input.nextInt();
        input.nextLine();

        System.out.print("Digite a data do registro (dd/MM/yyyy): ");
        LocalDate dataRegistro = LocalDate.parse(input.nextLine(), formatter);

        System.out.print("Nome: ");
        String nome = input.nextLine();

        System.out.print("Digite o nome do genitor: ");
        String nomeGenitor = input.nextLine();

        System.out.print("Digite o nome da genitora: ");
        String nomeGenitora = input.nextLine();

        System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
        LocalDate dataNascimento = LocalDate.parse(input.nextLine(), formatter);

        System.out.print("Digite o sexo (M/F): ");
        String sexo = input.nextLine();

        controller.registrar(termo, livro, folha, nome, nomeGenitor, nomeGenitora, dataNascimento, sexo, dataRegistro);

        input.close();
    }
}
