package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import javax.swing.JOptionPane;

import filemanipulation.ChooseFile;


public class DeleteTasks {
	static StringBuffer memoria = new StringBuffer();
	static String caminho = ChooseFile.path();

	public static void deleteTasks() {
		try {
			BufferedReader arqentrada;
			arqentrada = new BufferedReader(new FileReader(caminho));
			String nome = JOptionPane.showInputDialog("Digite o nome da tarefa que quer mudar");
			// nome = nome.toUpperCase();
			String linha;
			String descricao = "";
			String finalDate = "";
			Date inicialDate = null;
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
				int fim = memoria.indexOf("\n", primeiro);
				finalDate = lerTarefas(primeiro, fim);
				TaskRecord taskRecord = new TaskRecord(nome, descricao, finalDate, inicialDate);

				int resp = JOptionPane.showConfirmDialog(null, "Deseja excluir?" + "\n" + taskRecord.getNome() + "\n"
						+ taskRecord.getDescricao() + "\n" + taskRecord.getFinalDate());

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