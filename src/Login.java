import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JOptionPane;

import application.Program;
import filemanipulation.ChooseFile;

public class Login {
	static StringBuffer memoria = new StringBuffer();

	public static void login() {
		String email, password;
		try {
			String caminho = ChooseFile.path();
			BufferedReader arqentrada;
			arqentrada = new BufferedReader(new FileReader(caminho));

			email = JOptionPane.showInputDialog("Digite o e-mail");
			String linha;
			password = JOptionPane.showInputDialog("Digite a senha");

			while ((linha = arqentrada.readLine()) != null) {
				memoria.append(linha + "\r\n");
			}

			int inicio = -1;
			inicio = memoria.indexOf(email);

			if (inicio != -1) {
				int ultimo = memoria.indexOf("\t", inicio);
				email = lerConta(inicio, ultimo);
				int primeiro = ultimo + 1;
				ultimo = memoria.indexOf("\t", primeiro);
				// password = lerConta(primeiro, ultimo);
				///primeiro = ultimo + 1;
				int fim = memoria.indexOf("\n", primeiro);
				password = lerConta(primeiro, fim);

				Account account = new Account(email, password);

				
				if (account.getEmail() == email && account.getPassword() == password) {
					JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
					Program.main(null);;
				} 					
				//

				
			} else {
				JOptionPane.showMessageDialog(null, "Conta não registrada, se registre primeiro!");
			}
			arqentrada.close();

		} catch (Exception e) {

		}

	}

	public static String lerConta(int primeiro, int ultimo) {
		String dados = "";
		dados = memoria.substring(primeiro, ultimo);
		return dados;

	}
}