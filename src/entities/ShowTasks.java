package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import entitiesenum.TaskPriority;
import entitiesenum.TaskStatus;
import filemanipulation.ChooseFile;

public class ShowTasks {
	static StringBuffer memoria = new StringBuffer();
	static String caminho = ChooseFile.path();

	public static void showTasks() {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
		try {
			String caminho = ChooseFile.path();
			BufferedReader arqentrada;
			arqentrada = new BufferedReader(new FileReader(caminho));

			String nome = JOptionPane.showInputDialog("Digite o nome da tarefa para buscar");
			// nome = nome.toUpperCase();
			String linha, descricao = "", finalDate = "";
			Date finalDate1 = null, inicialDate = null;
			TaskPriority priority2 = null;;
			TaskStatus status = null;

			String strDate = dateFormat.format(finalDate1);

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
				//finalDate = lerTarefas(primeiro, ultimo);
				//primeiro = ultimo + 1;
				/*
				inicialDate = lerTarefas(primeiro, ultimo);
				primeiro = ultimo + 1;
				priority2 = lerTarefas(primeiro, ultimo);
				primeiro = ultimo + 1;
				*/
				int fim = memoria.indexOf("\n", primeiro);
				
				//finalDate = lerTarefas(primeiro, fim);
				
				
				TaskRecord taskRecord = new TaskRecord(nome, descricao, finalDate1, inicialDate, priority2, status);

				JOptionPane.showMessageDialog(null, taskRecord.getNome() + "\n" + taskRecord.getDescricao() + "\n" +
						taskRecord.getFinalDate());

				/*
				JOptionPane.showMessageDialog(null, taskRecord.getNome() + "\n" + taskRecord.getDescricao() + "\n" +
						taskRecord.getFinalDate() + "\n" + taskRecord.getPriority() + "\n" + taskRecord.getStatus());
				*/
				checkStatus(finalDate1);
				
				if (checkStatus(finalDate1) != TaskStatus.EM_PROGRESSO) {
					taskRecord.setStatus(status);
					memoria.replace(inicio, fim + 1,
							taskRecord.getNome() + "\t" + taskRecord.getDescricao() + "\t" + taskRecord.getFinalDate() + "\r\n");
					gravar();
					
				}
				/*
				JOptionPane.showMessageDialog(null, taskRecord.getNome() + "\n" + taskRecord.getDescricao() + "\n",
						taskRecord.getFinalDate() + "\n", taskRecord.getInitialDate() + "\n",
						taskRecord.getPriority() + "\n", taskRecord.getStatus() + "\n");
					*/
			} else {
				JOptionPane.showMessageDialog(null, "Tarefa não cadastrada");
			}
			arqentrada.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de leitura");
		}
	}

	public static String lerTarefas(int primeiro, int ultimo) {
		String tarefas = "";
		tarefas = memoria.substring(primeiro, ultimo);
		return tarefas;
	}

	public static TaskStatus checkStatus(Date finalDate1) {
		Date currentDate = new Date(System.currentTimeMillis());
		TaskStatus status;

		if (currentDate.after(finalDate1)) {
			status = TaskStatus.EXPIRADA;
		} else {
			status = TaskStatus.EM_PROGRESSO;
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