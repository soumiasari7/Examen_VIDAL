package components;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.*;


public class TestFonction extends JFrame{
                                 

	private static JProgressBar progressBar;
	private static JProgressBar progressBar2;
	static private JFileChooser fileChooser;
    static File serializedChooser = new File("chooser.ser");
    private static PrintWriter ecrire;
	@SuppressWarnings("deprecation")
	public static void saveFile(String contenu, String file,String path) {
			try {
				String d=path+"\\"+file+".txt";
				System.out.print(d);
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(d)));
	 
				writer.write(contenu);
	 
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	public static void main(String[] args) throws IOException  {

////=========================Boîte de saisie du nom de fichier contenant le résultat asprie======//		
//		JOptionPane jop = new JOptionPane();
//		JOptionPane.showInputDialog(null, "File name ", "Entree",
//				JOptionPane.QUESTION_MESSAGE);
//		
////==================enregistré  dans  un  emplacement selon le choix de l’utilisateur via  une boîte de dialogue==========//
//			JFileChooser filechoose = new JFileChooser();
//		// Créer un JFileChooser
//		   filechoose.setCurrentDirectory(new File(".")); 
//		   // Le répertoire source du JFileChooser est le répertoire d’où est lancé notre programme
//		   String approve = new String("Choisir l'emplacement d'enregistement du ficher aspire");
//		// Le bouton pour valider l’enregistrement portera la mention ENREGSITRER
//		   int resultatEnregistrer = filechoose.showDialog(filechoose,approve);
//		   // Pour afficher le JFileChooser…
//		   if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
//			  // Si l’utilisateur clique sur le bouton ENREGSITRER
//		   { String monFichier= new String(filechoose.getSelectedFile().toString());
//		// Récupérer le nom du fichier qu’il a spécifié
//		System.out.print("\n"+monFichier);
//		      } 
//		   
//
////============================Barres de progression=================================//
//		   
//		JPanel pan = new JPanel();
//		
////------------bar de progression spécifique à chaque page aspirée----------/
//		progressBar = new JProgressBar(0, 100);
//		progressBar.setValue(0);
//		progressBar.setStringPainted(true);
//		
////-------------bar de progression générale---------------------------------/
//        progressBar2 = new JProgressBar(0, 100);
//		progressBar2.setValue(0);
//		progressBar2.setStringPainted(true);
////-------------Les labels--------------------------------------------------/		
//		JLabel label2 = new JLabel("URL " );/*il faut la mette dans la boucle des letter pour genere les letter devent URL */
//		JLabel label = new JLabel("En cours...");
//		
////-------------La Fenantre------------------------------------------------/
//		JFrame frame = new JFrame("ProgressBarDemo");
//		frame.setTitle("Aspiration...");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setLocationRelativeTo(null);
//		
////------------ Ajout des composante il faut qu'il soit dans cette ordre a cause de la fontion .pack()----/
//		pan.add(progressBar);
//		pan.add(label2);
//		pan.add(progressBar2);
//		pan.add(label);
////------pour bien l'ordonne-----/		
//		JPanel panel1 = new JPanel();
//		panel1.setLayout(new BorderLayout());
//		panel1.add(pan, BorderLayout.NORTH);
//		panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
//		frame.setContentPane(panel1);
//		frame.pack();
//		frame.setVisible(true);
		
		/*
		JFileChooser dialogue = new JFileChooser(new File("."));
		PrintWriter sortie;
		File fichier;
		
		if (dialogue.showOpenDialog(null)== JFileChooser.APPROVE_OPTION)
		{
		    fichier = dialogue.getSelectedFile();
		    sortie = new PrintWriter (new FileWriter(fichier.getPath(), true));
		    sortie.println("sarahj");
		    sortie.close();
	}*/
		new Menu();
		
	}
}


		

