import java.util.Scanner;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner dado = new Scanner(System.in);
		int op;
		do {
			System.out.println();
			// new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
			// new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("\n\n-------- FOCUS - AREA DE LOGIN--------\n");
			System.out.println("< 1 > Registrar-se");
			System.out.println("< 2 > Fazer login");
			System.out.println("< 3 > SAIR");
			System.out.print("Opcao: ");
			op = dado.nextInt();
			switch (op) {
			case 1:
				Register.register();
				break;
			case 2:
				Login.login();
				break;
			case 3:			
				System.out.println("Programa encerrado com sucesso!");
				break;
			default:
				System.out.println("Opcao Invalida");
			}
		} while (op != 3);
	}
}