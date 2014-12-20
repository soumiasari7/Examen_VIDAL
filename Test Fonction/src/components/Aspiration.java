package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Aspiration extends JPanel {
	String urlstart = null;
	String nomfich;
	char c;
	private static PrintWriter ecrire;
	private static PrintWriter ecrire2;
	private static BufferedReader buff2;
	private static BufferedReader buff;
	static int length=1;
	
	private String nomfich2;
	
	

	public void bar() throws IOException {
		// =====================Creation boite de Saisi URL=========================//
			try {		urlstart = JOptionPane.showInputDialog(null, "URL", "Entree",
							JOptionPane.QUESTION_MESSAGE);
					
					
					URL url = new URL(urlstart);
					
					URLConnection conn = url.openConnection();
					BufferedReader in = new BufferedReader(new InputStreamReader(
							conn.getInputStream()));
					in.readLine();
					in.close();
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
 catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
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
				
				JFrame frame = new JFrame("ProgressBar");
				frame.setTitle("Aspiration...");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2 - frame.getHeight()/2);
				
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
				
				new JOptionPane();
				nomfich = JOptionPane.showInputDialog(null, "File name ","Entree", JOptionPane.QUESTION_MESSAGE);
				System.out.print("\n" + nomfich);

				
				if (nomfich.equals("subst")){
					JFileChooser chooser = new JFileChooser();
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setCurrentDirectory(new File("."));
					int resultatEnregistrer = chooser.showSaveDialog(chooser);
					String approve = new String("Ouvrir");


					if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
					// Si l’utilisateur clique sur le bouton ENREGSITRER
					{ chooser.getSelectedFile().getPath();
					  
					  //nomfich.endsWith(".txt")?nomfich:nomfich+".txt";
			          chooser.setName(nomfich);
					  
					
					}String path= chooser.getSelectedFile().getAbsolutePath();
					String file=saveFilein("", nomfich,path);
					//ecrire=new PrintWriter(file);
					ecrire=new PrintWriter(file);
					JOptionPane.showMessageDialog(null,"Debut de l’aspiration Medicament par substutance", "Message",JOptionPane.INFORMATION_MESSAGE);
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
							String line= lire.readLine();
							
							System.out.println(line);
							if (line == null) {
								break;
							}
							ecrire.write(line);
							// générer dans sauts de ligne
							ecrire.write(System.getProperty("line.separator"));
							// tant que c'est vrai (true)
							length++;
						} while (true);
						progressBar.setMaximum(length);
						length=1;
						// fermer le flux d'écriture
						ecrire.close();
						// concatenation
						ecrire = new PrintWriter(new FileOutputStream(file, true));
					}	// ========barre de progresion=========
					
					JOptionPane.showMessageDialog(null,"Fin de l’aspiration Medicament par substutance", "Message",JOptionPane.INFORMATION_MESSAGE);
					
				}
				if (nomfich.equals("medic")){
					JFileChooser chooser2 = new JFileChooser();
					chooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser2.setCurrentDirectory(new File("."));
					
					int resultatEnregistrer2 = chooser2.showSaveDialog(chooser2);
					String approve2 = new String("Ouvrir");


					if (resultatEnregistrer2 == JFileChooser.APPROVE_OPTION)
					// Si l’utilisateur clique sur le bouton ENREGSITRER
					{ chooser2.getSelectedFile().getPath();
					  
					  //nomfich.endsWith(".txt")?nomfich:nomfich+".txt";
			          chooser2.setName(nomfich);
					  
					
					}String path2= chooser2.getSelectedFile().getAbsolutePath();
					String file2=saveFilein("", nomfich,path2);
					//ecrire=new PrintWriter(file);
					try {
						ecrire2=new PrintWriter(file2);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null,"Debut de l’aspiration Medicament par nom comercial","Message", JOptionPane.INFORMATION_MESSAGE);
					for (c = 'A'; c <= 'Z'; c++) {
						if (c=='D'){JOptionPane.showMessageDialog(null, " problème de connexion à l'URL http://www.vidal.fr/Sommaires/Medicaments-D.htm !", "Message",JOptionPane.INFORMATION_MESSAGE);c='E';}
						else if (c=='M'){JOptionPane.showMessageDialog(null, " problème de connexion à l'URL http://www.vidal.fr/Sommaires/Medicaments-M.htm !", "Message",JOptionPane.INFORMATION_MESSAGE);
			            JOptionPane.showMessageDialog(null, " problème de connexion à l'URL http://www.vidal.fr/Sommaires/Medicaments-N.htm !", "Message",JOptionPane.INFORMATION_MESSAGE);c='O';}
						else if (c=='S'){JOptionPane.showMessageDialog(null, " problème de connexion à l'URL http://www.vidal.fr/Sommaires/Medicaments-S.htm !", "Message",JOptionPane.INFORMATION_MESSAGE);c='T';}
						
						progressBar2.setValue(c);
						label.setText("En cours ...");
						frame.pack();
						// URL url = new URL("http://www.vidal.fr/Sommaires/Medicaments-A.htm");
						// Ou bien...
						urlstart = "http://www.vidal.fr/Sommaires/Medicaments-" + c+ ".htm";
						// Ou bien PrintWriter ecrire =new PrintWriter (new FileWriter("subst.dic")) ;
						// FileWriter n'accepte pas un 2ème argument pour spécifier l'encodage
						
						// instancier un objet de la classe URL
						URL url = null;
						try {
							url = new URL(urlstart);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// imprimer cet objet
						System.out.println("URL à aspirer ==>" + url);
						BufferedReader lire = null;
						try {
							lire = new BufferedReader(new InputStreamReader(url.openStream()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						do
						// faire ce qui suit
						{
							progressBar.setValue(length);
							label2.setText("URL " + c);
							String line = null;
							try {
								line = lire.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(line);
							if (line == null) {
								break;
							}
							ecrire2.write(line);
							// générer dans sauts de ligne
							ecrire2.write(System.getProperty("line.separator"));
							// tant que c'est vrai (true)
							length++;
						} while (true);
						progressBar.setMaximum(length);
						length=1;
						ecrire2.close();
						// concatenation
						try {
							ecrire2 = new PrintWriter(new FileOutputStream(file2, true));
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// ========barre de progresion=========
						progressBar.setValue(0);
					}
					JOptionPane.showMessageDialog(null, "Fin de l’aspiration !", "Message",JOptionPane.INFORMATION_MESSAGE);
					}
				

}

	
	public static String saveFilein(String contenu, String file,String path) {
		String d=path+"\\"+file;
		try {
			System.out.print(d);
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(d)));
 
			writer.write(contenu);
 
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return d;
	}
	
}
