package components;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

public class Ouvrir implements ActionListener {
	JTextArea output;
	JScrollPane scrollPane;
	JButton charge;
	FileFilter filter;

	public Container createContentPane() {
		// Create the content-pane-to-be.
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		charge = new JButton("Charger le dico");
		charge.addActionListener(this);

		// Create a scrolled text area.
		output = new JTextArea(5, 30);
		output.setEditable(false);
		scrollPane = new JScrollPane(output);
		// Add the text area to the content pane.
		contentPane.add(scrollPane, BorderLayout.CENTER);

		contentPane.add(charge, BorderLayout.SOUTH);

		filter = new FileFilter() {

			@Override
			public String getDescription() {
				return "Fichiers DELA";
			}

			@Override
			public boolean accept(File f) {
				if (f.getName().endsWith("dic"))
					return true;
				return false;
			}
		};

		return contentPane;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == charge) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(filter);

			int returnVal = chooser.showOpenDialog(chooser);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String s = readFile(chooser.getSelectedFile().getPath());
				output.setText(s);
			}
		}
	}

	public String readFile(String file) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-16"));
			String ligne;
			StringBuffer fichier = new StringBuffer();

			while ((ligne = reader.readLine()) != null) {
				fichier.append(ligne);
				fichier.append("\n");
			}
			reader.close();

			return fichier.toString();
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Chargement du texte");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		Ouvrir demo = new Ouvrir();
		frame.setContentPane(demo.createContentPane());

		// Display the window.

		frame.pack();
		frame.setSize(700, 700);
		frame.setVisible(true);
	}
}
