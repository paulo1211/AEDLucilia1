package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import filemanipulation.ChooseFile;

public class ShowTasks {
	static StringBuffer memoria = new StringBuffer();
	static String caminho = ChooseFile.path();

	public static void showTasks() {
		String nome, descricao, linha = " ", priority, status, inicialDate,finalDate;

		try {
			String caminho = ChooseFile.path();
			BufferedReader arqentrada;
			arqentrada = new BufferedReader(new FileReader(caminho));

			nome = JOptionPane.showInputDialog("Digite o nome da tarefa para buscar");

			finalDate = JOptionPane
					.showInputDialog("Digite a data de término da tarefa, com o horário (DD/MM/YYYY HH:MM)");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime finalDate1 = LocalDateTime.parse(finalDate, formatter);

			while ((linha = arqentrada.readLine()) != null) {
				memoria.append(linha + "\r\n");
			}

			int inicio = -1;
			inicio = memoria.indexOf(nome);

			if (inicio != -1) {
				int ultimo = memoria.indexOf("\t", inicio);
				nome = lerTarefas(inicio, ultimo);
				int primeiro = ultimo + 1;
				ultimo = memoria.indexOf("\t", primeiro);
				descricao = lerTarefas(primeiro, ultimo);
				primeiro = ultimo + 1;

				ultimo = memoria.indexOf("\t", primeiro);
				finalDate = lerTarefas(primeiro, ultimo);
				primeiro = ultimo + 1;

				ultimo = memoria.indexOf("\t", primeiro);
				inicialDate = lerTarefas(primeiro, ultimo);
				primeiro = ultimo + 1;

				ultimo = memoria.indexOf("\t", primeiro);
				priority = lerTarefas(primeiro, ultimo);
				primeiro = ultimo + 1;

				int fim = memoria.indexOf("\n", primeiro);
				status = lerTarefas(primeiro, fim);

				TaskRecord taskRecord = new TaskRecord(nome, descricao, finalDate, inicialDate, priority, status);

			JOptionPane.showMessageDialog(null, "Nome da tarefa: " + taskRecord.getNome() + "\n" + "Descrição: " + taskRecord.getDescricao() + "\n"
					+ "Data de finalização: " + taskRecord.getFinalDate() + "\n" + "Data de inicialização: " + taskRecord.getInitialDate() + "\n"
					+ "Prioridade: " + taskRecord.getPriority() + "\n" + "Status: " + taskRecord.getStatus() + "\r\n");

				if (checkStatus(finalDate1) != "EM PROGRESSO") {
					taskRecord.setStatus("EM PROGRESSO");
					memoria.replace(inicio, fim + 1,
							taskRecord.getNome() + "\t" + taskRecord.getDescricao() + "\t" + taskRecord.getFinalDate()
									+ "\t" + taskRecord.getInitialDate() + "\t" + taskRecord.getPriority() + "\t"
									+ taskRecord.getStatus() + "\r\n");
					gravar();

				}

			} else {
				JOptionPane.showMessageDialog(null, "Tarefa não cadastrada");
			}
			arqentrada.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de leitura, você digitou corretamente?");
			System.out.println(e);
		}
	}

	public static String lerTarefas(int primeiro, int ultimo) {
		String tarefas = "";
		tarefas = memoria.substring(primeiro, ultimo);
		return tarefas;
	}

	public static String checkStatus(LocalDateTime finalDate1) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime currentDate = LocalDateTime.now();
		String inicialDate1 = currentDate.format(formatter);
		String status;

		if (currentDate.isBefore(finalDate1)) {
			status = "EXPIRADA";
		} else {
			status = "EM PROGRESSO";
		}

		return status;
	}

	public static void gravar() {
		try {
			BufferedWriter saida;
			saida = new BufferedWriter(new FileWriter(caminho));
			saida.write(memoria.toString());

			saida.flush();
			saida.close();
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Erro de gravação");
		}
	}
}