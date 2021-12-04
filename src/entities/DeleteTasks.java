package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import filemanipulation.ChooseFile;

public class DeleteTasks {
	static StringBuffer memoria = new StringBuffer();
	static String caminho = ChooseFile.path();

	public static void deleteTasks() {
		String nome, descricao, linha = " ", priority, status, inicialDate,finalDate;
		

		try {
			String caminho = ChooseFile.path();
			BufferedReader arqentrada;
			arqentrada = new BufferedReader(new FileReader(caminho));

			nome = JOptionPane.showInputDialog("Digite o nome da tarefa para deletar");

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

				int resp = JOptionPane.showConfirmDialog(null,
						"Deseja excluir?" + "\n" + "Nome da tarefa: " + taskRecord.getNome() + "\n" + "Descrição: "+ taskRecord.getDescricao() + "\n"
								+ "Data de finalização: " + taskRecord.getFinalDate() + "\n" + "Data de inicialização: " + taskRecord.getInitialDate() + "\n"
								+ "Prioridade: " + taskRecord.getPriority() + "\n" + "Status: " + taskRecord.getStatus() + "\r\n");

				if (resp == 0) {
					memoria.delete(inicio, fim + 1);
					gravar();
					JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Tarefa não existe");
			}
			arqentrada.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de leitura");
			System.out.println(e);
		}
	}

	public static String lerTarefas(int primeiro, int ultimo) {
		String dados = "";
		dados = memoria.substring(primeiro, ultimo);
		return dados;
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