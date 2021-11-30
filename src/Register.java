import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.JOptionPane;

import filemanipulation.ChooseFile;

public class Register {
	private static RandomAccessFile arquivo;

	public static void register() {
		Scanner input = new Scanner(System.in);
		String email, password;

		try {

			String caminho = ChooseFile.path();
			BufferedWriter saida;
			saida = new BufferedWriter(new FileWriter(caminho, true));

			email = JOptionPane.showInputDialog("Digite o seu e-mail");

			isValidEmailAddress(email);

			while (isValidEmailAddress(email) != true) {
				JOptionPane.showMessageDialog(null,
						"E-mail invalido, o padrão de e-mail é conta@dominio.com, digite novamente", "Atenção",
						JOptionPane.WARNING_MESSAGE);
				
				email = JOptionPane.showInputDialog("Digite o seu e-mail novamente");
			
			}
			
			password = JOptionPane.showInputDialog("Digite sua senha");
			
			Account account = new Account(email, password);
			
			
			saida.write(account.getEmail() + "\t");
			saida.write(account.getPassword() + "\r\n");
			saida.flush();
			saida.close();
			
			JOptionPane.showMessageDialog(null, "Usuário registrado com sucesso!");
			
		} catch (Exception e) {
			System.out.println("Usuário não registrado, tente novamente.");
		}
	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}
}