package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import filemanipulation.ChooseFile;

public class NewTasks {

	private static RandomAccessFile arquivo;

	public static void newTasks() {
		String nome, descricao, finalDate;

		try {
/*
			JOptionPane.showMessageDialog(null, "Escolha onde onde as tarefas ficarão armazenadas", "Atenção",
					JOptionPane.WARNING_MESSAGE);
*/
		//	String caminho = ChooseFile.path();
			String caminho = ChooseFile.path();
			BufferedWriter saida;
			saida = new BufferedWriter(new FileWriter(caminho, true));

			nome = JOptionPane.showInputDialog("Digite o nome da tarefa");

			descricao = JOptionPane.showInputDialog("Digite a descrição da tarefa");

			finalDate = JOptionPane
					.showInputDialog("Digite a data de término da tarefa, com o horário (DD/MM/YYYY HH:MM)");

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime finalDate1 = LocalDateTime.parse(finalDate, formatter);

			// captura a data do sistema, sendo a data inicial da tarefa.
			LocalDateTime inicialDate = LocalDateTime.now();
			String inicialDate1 = inicialDate.format(formatter);

			while (finalDate1.isBefore(inicialDate)) {
				finalDate = JOptionPane.showInputDialog("A data de término deve ser depois da data de hoje.");
				formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
				finalDate1 = LocalDateTime.parse(finalDate, formatter);
			}

			Object[] chooseStatus = { "Baixa", "Media", "Alta" };
			Object priority = JOptionPane.showInputDialog(null, "Selecione a prioridade da tarefa",
					"Prioridade da tarefa", JOptionPane.INFORMATION_MESSAGE, null, chooseStatus, chooseStatus[0]);

			// passa o objeto para String para armazenar
			String priority1 = (String) chooseStatus[0];

			// inicia a tarefa com o status padrão "iniciada"
			String status = "INICIADA";

			TaskRecord taskRecord = new TaskRecord(nome, descricao, finalDate, inicialDate1, priority1, status);

			saida.write(taskRecord.getNome() + "\t");
			saida.write(taskRecord.getDescricao() + "\t");
			saida.write(taskRecord.getInitialDate() + "\t");
			saida.write(taskRecord.getFinalDate() + "\t");
			saida.write(taskRecord.getPriority() + "\t");
			saida.write(taskRecord.getStatus() + "\r\n");
			saida.flush();
			saida.close();
			JOptionPane.showMessageDialog(null, "Tarefa gravada com sucesso!");
		} catch (Exception e) {
			System.out.println("Tarefa não gravada no arquivo.");
			System.out.println(e);
		}

	}
}