package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import entitiesenum.TaskPriority;
import entitiesenum.TaskStatus;
import filemanipulation.ChooseFile;

public class NewTasks {

	private static RandomAccessFile arquivo;

	public static void newTasks() {
		String nome, descricao, finalDate;
		SimpleDateFormat parseDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		//SimpleDateFormat parseDate = new SimpleDateFormat("dd/MM/yyyy");
		
		try {

			JOptionPane.showMessageDialog(null, "Escolha onde onde as tarefas ficarão armazenadas", "Atenção",
					JOptionPane.WARNING_MESSAGE);

			String caminho = ChooseFile.path();
			BufferedWriter saida;
			saida = new BufferedWriter(new FileWriter(caminho, true));

			nome = JOptionPane.showInputDialog("Digite o nome da tarefa");

			descricao = JOptionPane.showInputDialog("Digite a descrição da tarefa");

			finalDate = JOptionPane.showInputDialog("Digite a data de término da tarefa, com o horário");

			Date finalDate1 = parseDate.parse(finalDate);

			// captura a data do sistema, sendo a data inicial da tarefa.
			Date inicialDate = new Date(System.currentTimeMillis());

			while (finalDate1.before(inicialDate)) {
				finalDate = JOptionPane.showInputDialog("A data de término deve ser depois da data de hoje.");
				finalDate1 = parseDate.parse(finalDate);
			}


			Object priority = JOptionPane.showInputDialog(null, "Selecione a prioridade da tarefa",
					"Prioridade da tarefa", JOptionPane.INFORMATION_MESSAGE, null, TaskPriority.values(),
					TaskPriority.values()[0]);

			// gambiarra
			TaskPriority priority2 = (TaskPriority) priority;

			// inicia a tarefa com o status padrão "iniciada"
			TaskStatus status = TaskStatus.INICIADA;

			TaskRecord taskRecord = new TaskRecord(nome, descricao, finalDate1, inicialDate, priority2, status);

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