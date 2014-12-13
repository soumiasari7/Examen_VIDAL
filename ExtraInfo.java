package ExtraInfoProgDepart;

<<<<<<< HEAD
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

/*import java.util.regex.*; expression reg*/

public class ExtraInfo implements Runnable {
=======
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ExtraInfo {
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
	private static PrintWriter ecrire;
	private static PrintWriter ecrire2;

	public static void main(String[] args) throws IOException {

		char c;
		String urlstart = null;
		ecrire = new PrintWriter("subst");

		try {
<<<<<<< HEAD
			// =====================Creation boite de Saisi URL=========================//
=======
			//=====================Creation boite de Saisie URL=========================//
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
			urlstart = JOptionPane.showInputDialog(null, "URL", "Entree",JOptionPane.QUESTION_MESSAGE);
			URL url = new URL(urlstart);

			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			in.readLine();
			in.close();
<<<<<<< HEAD

			// =========================Boîte de saisie du nom de fichier contenant le résultat asprie======//
			new JOptionPane();
			String nomfich = JOptionPane.showInputDialog(null, "File name ","Entree", JOptionPane.QUESTION_MESSAGE);
			System.out.print("\n" + nomfich);
			// ======enregistré dans un emplacement selon le choix de l’utilisateur via une boîte de dialogue==========//

			JFileChooser filechoose = new JFileChooser();
			// Créer un JFileChooser
			filechoose.setCurrentDirectory(new File("."));
			// Le répertoire source du JFileChooser est le répertoire d’où est lancé notre programme
			String approve = new String(
					"Choisir l'emplacement d'enregistement du ficher aspire");
			// Le bouton pour valider l’enregistrement portera la mentionENREGSITRER

			int resultatEnregistrer = filechoose.showDialog(filechoose, approve);filechoose.setName(nomfich);
			// Pour afficher le JFileChooser…
			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
			// Si l’utilisateur clique sur le bouton ENREGSITRER
			{
				String monFichier = new String(filechoose.getSelectedFile().toString());
				// Récupérer le nom du fichier qu’il a spécifié
				System.out.print("\n" + monFichier);
			}
		}

		// ====================Erreur probleme connexion==============//

		catch (UnknownHostException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null,"Problème de connexion à Internet!", "Message",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		// ====================Erreur URL Vide========================//

=======
			

			//=========================BoÃ®te de saisie du nom de fichier contenant le rÃ©sultat asprie======//		
					JOptionPane jop = new JOptionPane();
					JOptionPane.showInputDialog(null, "File name ", "Entree",
							JOptionPane.QUESTION_MESSAGE);
					
			//==================enregistrÃ©  dans  un  emplacement selon le choix de lâ€™utilisateur via  une boÃ®te de dialogue==========//
						JFileChooser filechoose = new JFileChooser();
					// CrÃ©er un JFileChooser
					   filechoose.setCurrentDirectory(new File(".")); 
					   // Le rÃ©pertoire source du JFileChooser est le rÃ©pertoire dâ€™oÃ¹ est lancÃ© notre programme
					   String approve = new String("Choisir l'emplacement d'enregistement du ficher aspire");
					// Le bouton pour valider lâ€™enregistrement portera la mention ENREGSITRER
					   int resultatEnregistrer = filechoose.showDialog(filechoose,approve);
					   // Pour afficher le JFileChooserâ€¦
					   if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
						  // Si lâ€™utilisateur clique sur le bouton ENREGSITRER
					   { String monFichier= new String(filechoose.getSelectedFile().toString());
					// RÃ©cupÃ©rer le nom du fichier quâ€™il a spÃ©cifiÃ©
					System.out.print("\n"+monFichier);
					      } 
		}

		//====================Erreur probleme connexion==============//
		
		catch (UnknownHostException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null,
					"ProblÃ¨me de connexion Ã  Internet!", "Message",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		//====================Erreur URL Vide========================//
		
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
		catch (MalformedURLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Vous n'avez pas saisie l'URL","Message", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		// =====================URL inexistante (dans ce cas l’aspiration doit
		// continuer)================//
		/* ou l (UnknownHostException */
		catch (FileNotFoundException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Problème de connexion à URL "+ urlstart, "Message", JOptionPane.ERROR_MESSAGE);
		}
		// ============================Barres de progression=================================//

		JPanel pan = new JPanel();

		// ------------bar de progression spécifique à chaque page aspirée----------/
		JProgressBar progressBar = new JProgressBar('A', 'B');
		progressBar.setValue('A');
		progressBar.setStringPainted(true);

		// -------------bar de progression générale---------------------------------/
		JProgressBar progressBar2 = new JProgressBar('A', 'Z');
		progressBar2.setValue('A');
		progressBar2.setStringPainted(true);
		// -------------les labels--------------------------------------------------/
		JLabel label2 = new JLabel("URL");/* il faut la mette dans la boucle desletter pour genere les letter devent URL*/
		JLabel label = new JLabel("En cours...");

		// -------------LaFenantre------------------------------------------------/
		JFrame frame = new JFrame("ProgressBarDemo");
		frame.setTitle("Aspiration...");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// ------------ Ajout des composante il faut qu'il soit dans cette ordre a cause de la fontion .pack()----/
		pan.add(progressBar);
		pan.add(label2);
		pan.add(progressBar2);
		pan.add(label);
		// ------pour bien l'ordonne-----/
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel1.add(pan, BorderLayout.NORTH);
		panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		frame.setContentPane(panel1);

		frame.setVisible(true);

		// progressBar.setValue(d);

		for (c = 'A'; c <= 'Z'; c++) {
			progressBar2.setValue(c);
			label.setText("En cours ...");
			frame.pack();
			// URL url = new
			// URL("http://www.vidal.fr/Sommaires/Substances-C.htm");
			// Ou bien...
			urlstart = "http://www.vidal.fr/Sommaires/Substances-" + c + ".htm";

			// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
<<<<<<< HEAD
			// FileWriter n'accepte pas un 2ème argument pour spécifierl'encodage

=======
			//FileWriter n'accepte pas un 2Ã¨me argument pour spÃ©cifier l'encodage
			
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
			// instancier un objet de la classe URL
			URL url = new URL(urlstart);

			// imprimer cet objet
<<<<<<< HEAD
			System.out.println("URL à aspirer ==>" + url);
=======
			System.out.println("URL Ã  aspirer ==>" + url);
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379

			BufferedReader lire = new BufferedReader(new InputStreamReader(url.openStream()));

			do
			// faire ce qui suit
			{
				progressBar.setValue(c);
				label2.setText("URL " + c);
				String line = lire.readLine();

				System.out.println(line);
				if (line == null) {
					break;
				}

				ecrire.write(line);
<<<<<<< HEAD

				// générer dans sauts de ligne
=======
				
				// gÃ©nÃ©rer dans sauts de ligne
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
				ecrire.write(System.getProperty("line.separator"));

				// tant que c'est vrai (true)
			} while (true);
<<<<<<< HEAD

			// fermer le flux d'écriture
=======
			
            // fermer le flux d'Ã©criture
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
			ecrire.close();

			// concatenation
			ecrire = new PrintWriter(new FileOutputStream("subst", true));

			/* ========barre de progresion========= */
			progressBar.setValue(0);
		}

<<<<<<< HEAD
		// ////////////////////////////////////////////////////////////////////
		// ///////////// Les Médicaments par Nom Commercial///////////////////
		// ////////////////////////////////////////////////////////////////////
=======
		 //////////////////////////////////////////////////////////////////////
		/////////////// Les MÃ©dicaments par Nom Commercial///////////////////
		//////////////////////////////////////////////////////////////////////
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379

		ecrire2 = new PrintWriter("medic");

		for (c = 'A'; c <= 'Z'; c++) {

			// URL url = new
			// URL("http://www.vidal.fr/Sommaires/Medicaments-A.htm");
			// Ou bien...
			urlstart = "http://www.vidal.fr/Sommaires/Medicaments-" + c+ ".htm";

<<<<<<< HEAD
			// spécifier l'URL (plus tard), elle sera insérée dans une boîte de
			// saisie de type InputDialog

			// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
			// FileWriter n'accepte pas un 2ème argument pour spécifier l'encodage

=======
			// spÃ©cifier l'URL (plus tard), elle sera insÃ©rÃ©e dans une boÃ®te de saisie de type InputDialog

			// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
			//FileWriter n'accepte pas un 2Ã¨me argument pour spÃ©cifier l'encodage
			
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
			// instancier un objet de la classe URL
			URL url = new URL(urlstart);

			// imprimer cet objet
<<<<<<< HEAD
			System.out.println("URL à aspirer ==>" + url);
=======
			System.out.println("URL Ã  aspirer ==>" + url);
			
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379

			BufferedReader lire = new BufferedReader(new InputStreamReader(url.openStream()));

			do
			// faire ce qui suit
			{
				String line = lire.readLine();

				System.out.println(line);
				if (line == null) {
					break;
				}

				ecrire2.write(line);
<<<<<<< HEAD

				// générer dans sauts de ligne
=======
				
				// gÃ©nÃ©rer dans sauts de ligne
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
				ecrire2.write(System.getProperty("line.separator"));

				// tant que c'est vrai (true)
			} while (true);
<<<<<<< HEAD

			// fermer le flux d'écriture
=======
			
			// fermer le flux d'Ã©criture
>>>>>>> 55392a38fc94ac15dea9d8bba7ff5635570af379
			ecrire2.close();

			// concatenation
			ecrire2 = new PrintWriter(new FileOutputStream("medic", true));
		}

	}

	public void run() {
		// TODO Auto-generated method stub

	}
}
