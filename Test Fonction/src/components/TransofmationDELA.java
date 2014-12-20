package components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TransofmationDELA {

	private static Pattern pattern;
	private static Matcher matcher;
	private BufferedReader buff2;
	private BufferedWriter ecrire4;
	private String file;
	private BufferedWriter ecrire3;
	private BufferedReader buff;

	public void Tans() throws IOException {
		JFileChooser chooser2 = new JFileChooser();
		int returnVal = chooser2.showOpenDialog(chooser2);
		File myFile = chooser2.getSelectedFile();
		String path=myFile.getAbsolutePath();
		path= path  ;
		System.out.println(path);

		if (myFile.getName().equals("subst")) {
			
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setCurrentDirectory(new File("."));

			int resultatEnregistrer = chooser.showSaveDialog(chooser);
			String approve = new String("Ouvrir");

			if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
			// Si l’utilisateur clique sur le bouton ENREGSITRER
			{
				chooser.getSelectedFile().getPath();
//				chooser.setName("subst");

			}
			String path2 = chooser.getSelectedFile().getAbsolutePath();
			System.out.println("\n"+path2+"\n");
			String file = saveFilein("", "subst.dic", path2);
			System.out.println("\n"+file+"\n");
             
			TranSubst(file,path);
		}
		
	
		else if (myFile.getName().equals("medic")) {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setCurrentDirectory(new File("."));

		int resultatEnregistrer = chooser.showSaveDialog(chooser);
		String approve = new String("Ouvrir");

		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION)
		// Si l’utilisateur clique sur le bouton ENREGSITRER
		{
			chooser.getSelectedFile().getPath();
//			chooser.setName("subst");

		}
		String path2 = chooser.getSelectedFile().getAbsolutePath();
		System.out.println("\n"+path2+"\n");
		String file = saveFilein("", "medic.dic", path2);
		System.out.println("\n"+file+"\n");
         
		TranMedic(file,path);
		
	}
		else {JOptionPane.showMessageDialog(null, "chossier subst ou medic !","Message", JOptionPane.INFORMATION_MESSAGE);}

}	
	

	private void TranMedic(String file2, String path) throws IOException {
		buff = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
		ecrire3 =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "UTF-16"));

		ArrayList<String> arrayOfStrings = new ArrayList<String>();
		do
		// faire ce qui suit
		{

			String line = buff.readLine();
			if (line == null) {
				break;
			}
			String s = recherche(line);
			System.out.println(s);
			
			if (s!="") { 
				arrayOfStrings.add(s);
                HashSet<String> h = new HashSet<String>(arrayOfStrings);
                arrayOfStrings.clear();
                arrayOfStrings.addAll(h);
			
                }
		} while (true);
		
		  /*=====trier la liste======*/
		  Collections.sort(arrayOfStrings);
		  
		 for (String readline : arrayOfStrings){
			
			 ecrire3.write( readline.toLowerCase() + ",.N+medic"+"\n");
			
		     }
		// fermer le flux d'écriture
		ecrire3.close();
		// concatenation
		ecrire3 =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-16"));
		
		JOptionPane.showMessageDialog(null, "Fin de creation mdic.dic !", "Message",JOptionPane.INFORMATION_MESSAGE);
		
	}

	public void TranSubst(String file,String path) throws IOException {

		buff2 = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

		ecrire4 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-16"));
		do
		// faire ce qui suit
		{

			String line = buff2.readLine();
			if (line == null) {
				break;
			}
			String s = recherche2(line);
			System.out.println(s);

			if (s != "") {
				ecrire4.write(s + ",.N+subst");
				ecrire4.write(System.getProperty("line.separator"));
			}

		} while (true);
		// fermer le flux d'écriture
		ecrire4.close();
		// concatenation
		ecrire4 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-16"));
		
		JOptionPane.showMessageDialog(null, "Fin de creation subst.dic !","Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public static String recherche2(String ligne1) {
		String res1 = "";
		pattern = Pattern.compile("<a href=\"Substance/.*>(.*)?<");
		matcher = pattern.matcher(ligne1);

		boolean matchFound = matcher.find();

		if (matchFound && matcher.groupCount() >= 1) {

			res1 = matcher.group(1);
		}
		return res1;
	}
	
	public static String recherche(String ligne){
		String res="";
		   pattern = Pattern.compile("<a href=\"Medicament/.*\">(\\D\\S*)$*");
		   matcher = pattern.matcher(ligne);


		    boolean matchFound = matcher.find();


		    if (matchFound && matcher.groupCount()>=1) {


		        res = matcher.group(1);
		    }
		    return res;
	}

	public static String saveFilein(String contenu, String file, String path) {
		String d = path + "\\" + file;
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