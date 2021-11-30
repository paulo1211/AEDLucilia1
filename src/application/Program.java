package application;
import java.io.IOException;
import java.util.Scanner;

import entities.ChangeTasks;
import entities.DeleteTasks;
import entities.NewTasks;
import entities.ShowTasks;

public class Program {

	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner dado = new Scanner(System.in);
		int op;
		do {
			System.out.println();
			// new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
			// new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			System.out.println("\n\n-------- FOCUS - O SEU APLICATIVO DE PRODUTIVIDADE --------\n");
			System.out.println("< 1 > Adicionar tarefa");
			System.out.println("< 2 > Listar tarefas");
			System.out.println("< 3 > Excluir tarefa");
			System.out.println("< 4 > Modificar tarefas");
			System.out.println("< 5 > SAIR");
			System.out.print("Opcao: ");
			op = dado.nextInt();
			switch (op) {
			case 1:
				NewTasks.newTasks();
				break;
			case 2:
				ShowTasks.showTasks();
				break;
			case 3:
				DeleteTasks.deleteTasks();
				break;

			case 4:
				ChangeTasks.changeTasks();
				break;

			case 5:
				System.out.println("Programa finalizado :)");
				break;

			default:
				System.out.println("Opcao Invalida");
			}
		} while (op != 5);
	}
}