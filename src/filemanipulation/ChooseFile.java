package filemanipulation;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.lang.System;

public class ChooseFile {

	public static String path() {
		int result;

		
		/// teste para escolher o arquivo de acordo com o sistema operacional
		String os = System.getProperty("os.name");

		if (os.equals("Linux")) {
			File arquivo = new File("\\\\home\\\\paulo\\\\filename.txt");
		} else {
			if (os.startsWith("Windows")) {
				//
			}
		}
		//
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		result = fileChooser.showOpenDialog(null);

		if (result == fileChooser.CANCEL_OPTION) {
			return null;
		}
		File arquivo = fileChooser.getSelectedFile();

		if (arquivo == null || arquivo.getName().equals("")) {
			JOptionPane.showMessageDialog(null, "Nome de arquivo inválido, digite novamente.");
		} else {
			return arquivo.getPath();
		}

		return null;
	}

}