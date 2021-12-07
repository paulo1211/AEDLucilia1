package filemanipulation;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ChooseFile {

	public static String path() {
		int result;
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		result = fileChooser.showOpenDialog(null);

		if (result == fileChooser.CANCEL_OPTION) {
			return null;
		}
		File arquivo = fileChooser.getSelectedFile();

		if (arquivo == null || arquivo.getName().equals("")) {
			JOptionPane.showMessageDialog(null, "Nome de arquivo invalido, digite novamente.");
		} else {
			return arquivo.getPath();
		}

		return null;
	}

}