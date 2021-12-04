package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import filemanipulation.ChooseFile;

public class ChangeTasks {
	static StringBuffer memoria = new StringBuffer();
	static String caminho = ChooseFile.path();

	public static void changeTasks() {
		String nome, descricao, linha = " ", priority, status, finalDate, inicialDate;
	
		try {
			String caminho = ChooseFile.path();
			BufferedReader arqentrada;
			arqentrada = new BufferedReader(new FileReader(caminho));

			nome = JOptionPane.showInputDialog("Digite o nome da tarefa que quer mudar");
			finalDate = JOptionPane.showInputDialog("Digite a data de término da tarefa, com o horário (DD/MM/YYYY HH:MM)");

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
			
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				LocalDateTime finalDate1 = LocalDateTime.parse(finalDate, formatter);
				LocalDateTime inicialDate1 = LocalDateTime.parse(inicialDate, formatter);
				
				TaskRecord taskRecord = new TaskRecord(nome, descricao, finalDate, inicialDate, priority, status);

				nome = JOptionPane.showInputDialog("Entre com o novo nome da tarefa");
				taskRecord.setNome(nome);
				
				descricao = JOptionPane.showInputDialog("Entre com a nova descrição da tarefa");
				taskRecord.setDescricao(descricao);
				
				finalDate = JOptionPane.showInputDialog("Entre com a nova data de término, com o horário (DD/MM/YYYY HH:MM)");
				taskRecord.setFinalDate(finalDate);
				
				priority = JOptionPane.showInputDialog("Entre com a nova prioridade");
				taskRecord.setPriority(priority);
				
				
				memoria.replace(inicio, fim + 1, taskRecord.getNome() + "\t" + taskRecord.getDescricao() + "\t"
						+ taskRecord.getFinalDate() + "\t"+ taskRecord.getPriority() + "\r\n");
				gravar();

				JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Tarefa não cadastrada");
			}
			arqentrada.close();

		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, "Erro de leitura, você digitou corretamente?");
			System.out.println(error);
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