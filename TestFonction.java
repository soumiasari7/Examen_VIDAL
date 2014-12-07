import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TestFonction {

	public static void main(String[] args) {

//=========================Boîte de saisie du nom de fichier contenant le résultat asprie======//		
		JOptionPane jop = new JOptionPane();
		JOptionPane.showInputDialog(null, "File name ", "Entree",
				JOptionPane.QUESTION_MESSAGE);
		
//==================enregistré  dans  un  emplacement selon le choix de l’utilisateur via  une boîte de dialogue==========//
			JFileChooser filechoose = new JFileChooser();
		// Créer un JFileChooser
		   filechoose.setCurrentDirectory(new File(".")); 
		   // Le répertoire source du JFileChooser est le répertoire d’où est lancé notre programme
		   String approve = new String("Choisir l'emplacement d'enregistement du ficher aspire");
		// Le bouton pour valider l’enregistrement portera la mention ENREGSITRER
		   int resultatEnregistrer = filechoose.showDialog(filechoose,approve);
		   // Pour afficher le JFileChooser…
		   if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
			  // Si l’utilisateur clique sur le bouton ENREGSITRER
		   { String monFichier= new String(filechoose.getSelectedFile().toString());
		// Récupérer le nom du fichier qu’il a spécifié
		System.out.print("\n"+monFichier);
		      } 
		   }
//============================Barres de progression=================================//
	
	
	}
	
