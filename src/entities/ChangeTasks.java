package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

import javax.swing.JOptionPane;

import filemanipulation.ChooseFile;

public class ChangeTasks {
	static StringBuffer memoria = new StringBuffer();
	static String caminho = ChooseFile.path();

	public static void changeTasks() {
		try {
			BufferedReader arqentrada;
			arqentrada = new BufferedReader(new FileReader(caminho));
			String nome = JOptionPane.showInputDialog("Digite o nome da tarefa que quer mudar");
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

				TaskRecord taskRecord = new TaskRecord(nome, descricao, finalDate1, inicialDate, priority2, status);

				JOptionPane.showMessageDialog(null, taskRecord.getNome() + "\n" + taskRecord.getDescricao());
				nome = JOptionPane.showInputDialog("Entre com o novo nome da tarefa");
				nome = nome.toUpperCase();
				taskRecord.setNome(nome);
				descricao = JOptionPane.showInputDialog("Entre com a nova descrição da tarefa");
				taskRecord.setDescricao(descricao);
				memoria.replace(inicio, fim + 1, taskRecord.getNome() + "\t" + taskRecord.getDescricao() + "\t"
						+ taskRecord.getFinalDate() + "\r\n");
				gravar();

				JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso");
			} else {
				JOptionPane.showMessageDialog(null, "Tarefa não cadastrada");
			}
			arqentrada.close();

		} catch (Exception error) {
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