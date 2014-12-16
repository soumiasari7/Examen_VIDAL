package ExtraInfoProgDepart;

import java.awt.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

import java.util.regex.*; /* expression reg*/
public class ExtraInfo {
	private static PrintWriter ecrire;
	private static PrintWriter ecrire2;
    private static Pattern pattern;
	private static Matcher matcher;
	private static PrintWriter ecrire3;
	private static PrintWriter ecrire4;
	private static BufferedReader buff2;
	private static BufferedReader buff;
	static int length=1;
	public static String recherche(String ligne){
		String res="";
		   pattern = Pattern.compile("<a href=\"Medicament.*>(.*)?<");
		   matcher = pattern.matcher(ligne);


		    boolean matchFound = matcher.find();


		    if (matchFound && matcher.groupCount()>=1) {


		        res = matcher.group(1);
		    }
		    return res;
	}
	
	public static String recherche2(String ligne1){
		String res1="";
		   pattern = Pattern.compile("<a href=\"Substance/.*>(.*)?<");
		   Matcher matcher = pattern.matcher(ligne1);


		    boolean matchFound = matcher.find();


		    if (matchFound && matcher.groupCount()>=1) {


		        res1 = matcher.group(1);
		    }
		    return res1;
	}
	
	public static void main(String[] args) throws IOException {
		char c;
		String urlstart = null;
		
		try {
// =====================Creation boite de Saisi URL=========================//
			urlstart = JOptionPane.showInputDialog(null, "URL", "Entree",
					JOptionPane.QUESTION_MESSAGE);
			URL url = new URL(urlstart);
			URLConnection conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			in.readLine();
			in.close();
			
// =========================Boîte de saisie du nom de fichier contenant le résultat asprie======//
			new JOptionPane();
			String nomfich = JOptionPane.showInputDialog(null, "File name ","Entree", JOptionPane.QUESTION_MESSAGE);
			System.out.print("\n" + nomfich);
			
// ======enregistré dans un emplacement selon le choix de l’utilisateur via une boîte de dialogue==========//
			JFileChooser filesave = new JFileChooser();
             /* Créer un JFileChooser */
			filesave.setName(nomfich);
			filesave.setCurrentDirectory(new File("."));
			/* Le répertoire source du JFileChooser est le répertoire d’où est lancé notre programme*/
			String approve = new String("Ouvrir");
			/* Le bouton pour valider l’enregistrement portera la mentionENREGSITRER*/
			int resultatEnregistrer = filesave.showSaveDialog(filesave);
			String Path=filesave.getSelectedFile().getPath();
			// Pour afficher le JFileChooser…
			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
			// Si l’utilisateur clique sur le bouton ENREGSITRER
			{
				String monFichier = new String(filesave.getSelectedFile().toString());
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
		
		catch (MalformedURLException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Vous n'avez pas saisie l'URL","Message", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
// =====================URL inexistante (dans ce cas l’aspiration doit continuer)================/
		
		catch (FileNotFoundException e) {
			System.err.println(e);
			JOptionPane.showMessageDialog(null, "Problème de connexion à URL "+ urlstart, "Message", JOptionPane.ERROR_MESSAGE);
		}
// ============================Barres de progression=================================//
	
		JPanel pan = new JPanel();
		
// ------------bar de progression spécifique à chaque page aspirée----------/
		
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		
// -------------------bar de progression générale-----------------------------/
		
		JProgressBar progressBar2 = new JProgressBar('A', 'Z');
		progressBar2.setValue('A');
		progressBar2.setStringPainted(true);
		
// -------------les labels--------------------------------------------------/
		
		JLabel label2 = new JLabel("URL");/* il faut la mette dans la boucl desletter pour genere les letter devent URL*/
		JLabel label = new JLabel("En cours...");
		
// -------------La Fenantre-----------------------------------------------//
		
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

                    /* ==================================================================*
                     *                                                                   *
                     *                   Les Médicaments par Substutance                 *
                     *                                                                   *
                     * ==================================================================*/
		
		JOptionPane.showMessageDialog(null,"Debut de l’aspiration Medicament par substutance", "Message",JOptionPane.INFORMATION_MESSAGE);
		ecrire = new PrintWriter("subst");
		for (c = 'A'; c <= 'Z'; c++) {
			progressBar2.setValue(c);
			label.setText("En cours ...");
			frame.pack();
			// URL url = new URL("http://www.vidal.fr/Sommaires/Substances-C.htm");
			// Ou bien...
			urlstart = "http://www.vidal.fr/Sommaires/Substances-" + c + ".htm";
			// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
			// FileWriter n'accepte pas un 2ème argument pour spécifierl'encodage
			
			// instancier un objet de la classe URL
			URL url = new URL(urlstart);
			// imprimer cet objet
			System.out.println("URL à aspirer ==>" + url);
			BufferedReader lire = new BufferedReader(new InputStreamReader(url.openStream()));
			do
			// faire ce qui suit
			{   progressBar.setValue(length);
				label2.setText("URL " + c);
				String line = lire.readLine();
				System.out.println(line);
				if (line == null) {
					break;
				}
				ecrire.write(line);
				// générer dans sauts de ligne
				ecrire.write(System.getProperty("line.separator"));
				length++;
			} while (true);
			progressBar.setMaximum(length);
			length=1;
			// fermer le flux d'écriture
			ecrire.close();
			// concatenation
			ecrire = new PrintWriter(new FileOutputStream("subst", true));
			// ========barre de progresion=========
		}
		JOptionPane.showMessageDialog(null,"Fin de l’aspiration Medicament par substutance", "Message",JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null,"Debut de l’aspiration Medicament par nom comercial","Message", JOptionPane.INFORMATION_MESSAGE);

		/* ==================================================================*
         *                                                                   *
         *                   Les Médicaments par Nom Commercial              *
         *                                                                   *
         * ==================================================================*/

		ecrire2 = new PrintWriter("medic");

// =========================Boîte de saisie du nom de fichier contenant le résultat asprie======//
		
		new JOptionPane();
		String nomfich = JOptionPane.showInputDialog(null, "File name ","Entree", JOptionPane.QUESTION_MESSAGE);
		System.out.print("\n" + nomfich);
		
// ======enregistré dans un emplacement selon le choix de l’utilisateur via une boîte de dialogue==========//
		
		JFileChooser filechoose = new JFileChooser();
		// Créer un JFileChooser
		filechoose.setCurrentDirectory(new File("."));
		// Le répertoire source du JFileChooser est le répertoire d’où est lancé
		// notre programme
		String approve = new String("Choisir l'emplacement d'enregistement du ficher aspire");
		// Le bouton pour valider l’enregistrement portera la mentionENREGSITRER
		int resultatEnregistrer = filechoose.showDialog(filechoose, approve);
		filechoose.setName(nomfich);
		// Pour afficher le JFileChooser…
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
		// Si l’utilisateur clique sur le bouton ENREGSITRER
		{
			String monFichier = new String(filechoose.getSelectedFile()
					.toString());
			// Récupérer le nom du fichier qu’il a spécifié
			System.out.print("\n" + monFichier);
		}

		for (c = 'A'; c <= 'Z'; c++) {
			progressBar2.setValue(c);
			label.setText("En cours ...");
			frame.pack();
			// URL url = new URL("http://www.vidal.fr/Sommaires/Medicaments-A.htm");
			// Ou bien...
			urlstart = "http://www.vidal.fr/Sommaires/Medicaments-" + c+ ".htm";
			// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
			// FileWriter n'accepte pas un 2ème argument pour spécifier l'encodage
			
			// instancier un objet de la classe URL
			URL url = new URL(urlstart);
			// imprimer cet objet
			System.out.println("URL à aspirer ==>" + url);
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
				ecrire2.write(line);
				// générer dans sauts de ligne
				ecrire2.write(System.getProperty("line.separator"));
				// tant que c'est vrai (true)
			} while (true);
			// fermer le flux d'écriture
			ecrire2.close();
			// concatenation
			ecrire2 = new PrintWriter(new FileOutputStream("medic", true));
			// ========barre de progresion=========
			progressBar.setValue(0);
		}
		JOptionPane.showMessageDialog(null, "Fin de l’aspiration !", "Message",JOptionPane.INFORMATION_MESSAGE);
		progressBar2.disable();;
//========================================================================================//
//                           ETAP2:lecture fichier medic                                  //
//========================================================================================//
		buff = new BufferedReader(new FileReader("medic"));
		ecrire3 = new PrintWriter("medic.dic");

		do
		// faire ce qui suit
		{

			String line = buff.readLine();
			if (line == null) {
				break;
			}
			String s = recherche(line);
			System.out.println(s);
			
			if (s!="") {ecrire3.write(s);
			ecrire3.write(System.getProperty("line.separator"));}
		} while (true);
		// fermer le flux d'écriture
		ecrire3.close();
		// concatenation
		ecrire3 = new PrintWriter(new FileOutputStream("medic.dic", true));
		JOptionPane.showMessageDialog(null, "Fin de creation mdic.dic !", "Message",JOptionPane.INFORMATION_MESSAGE);
		ecrire3.close();
//========================================================================================//
//		                     ETAP2:lecture fichier subst                                  //
//========================================================================================//
		
		buff2 = new BufferedReader(new FileReader("subst"));
        ecrire4 = new PrintWriter("subst.dic");
		do
		// faire ce qui suit
		{

			String line = buff2.readLine();
			if (line == null) {
				break;
			}
			String s = recherche2(line);
			System.out.println(s);
			
			if (s!="") {ecrire4.write(s);
			ecrire4.write(System.getProperty("line.separator"));}

		} while (true);
		// fermer le flux d'écriture
		ecrire4.close();
		// concatenation
		ecrire4 = new PrintWriter(new FileOutputStream("subst.dic", true));
		JOptionPane.showMessageDialog(null, "Fin de creation subst.dic !", "Message",JOptionPane.INFORMATION_MESSAGE);

	}

}
