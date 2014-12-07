package ExtraInfoProgDepart;

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
	private static PrintWriter ecrire;
	private static PrintWriter ecrire2;

	public static void main(String[] args) throws IOException {
		char c;
		String urlstart;
		ecrire = new PrintWriter("subst");

		try {
			//=====================Creation boite de Saisie URL=========================//
			urlstart = JOptionPane.showInputDialog(null, "URL", "Entree",JOptionPane.QUESTION_MESSAGE);
			URL url = new URL(urlstart);

			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			in.readLine();
			in.close();
			

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

		//====================Erreur probleme connexion==============//
		
		catch (UnknownHostException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null,
					"Problème de connexion à Internet!", "Message",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		//====================Erreur URL Vide========================//
		
		catch (MalformedURLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Vous n'avez pas saisie l'URL",
					"Message", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		for (c = 'A'; c <= 'Z'; c++) {

			// URL url = new URL("http://www.vidal.fr/Sommaires/Substances-C.htm");
			// Ou bien...
			urlstart = "http://www.vidal.fr/Sommaires/Substances-" + c + ".htm";

			// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
			//FileWriter n'accepte pas un 2ème argument pour spécifier l'encodage
			
			// instancier un objet de la classe URL
			URL url = new URL(urlstart);
            
			// imprimer cet objet
			System.out.println("URL à aspirer ==>" + url);

			
			BufferedReader lire = new BufferedReader(new InputStreamReader(url.openStream()));

			do
			// faire ce qui suit
			{
				String line = lire.readLine();

				System.out.println(line);
				if (line == null) {
					break;
				}

				ecrire.write(line);
				
				// générer dans sauts de ligne
				ecrire.write(System.getProperty("line.separator"));

			// tant que c'est vrai (true)	
			} while (true);
			
            // fermer le flux d'écriture
			ecrire.close();
			
			//concatenation 
			ecrire = new PrintWriter(new FileOutputStream("subst", true));
		}
		

		 //////////////////////////////////////////////////////////////////////
		/////////////// Les Médicaments par Nom Commercial///////////////////
		//////////////////////////////////////////////////////////////////////

		ecrire2 = new PrintWriter("medic");
		
		for (c = 'A'; c <= 'Z'; c++) {

			// URL url = new URL("http://www.vidal.fr/Sommaires/Medicaments-A.htm");
			// Ou bien...
			urlstart = "http://www.vidal.fr/Sommaires/Medicaments-" + c+ ".htm";

			// spécifier l'URL (plus tard), elle sera insérée dans une boîte de saisie de type InputDialog

			// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
			//FileWriter n'accepte pas un 2ème argument pour spécifier l'encodage
			
			// instancier un objet de la classe URL
			URL url = new URL(urlstart);

			// imprimer cet objet
			System.out.println("URL à aspirer ==>" + url);
			

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
				
				// générer dans sauts de ligne
				ecrire2.write(System.getProperty("line.separator"));
				
             // tant que c'est vrai (true)
			} while (true);
			
			// fermer le flux d'écriture
			ecrire2.close();
			
			//concatenation 
			ecrire2 = new PrintWriter(new FileOutputStream("medic", true));
		}
		
	}
}
