package components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;



 @SuppressWarnings("serial")
class Panneau extends JPanel {
	 Ouvrir o=new Ouvrir();
	 public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("EI.jpg"));
	      //g.drawImage(img, 0, 0, this);
	      //Pour une image de fond
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }                
	  
	  }
 }
	
	@SuppressWarnings("serial")
	class feneter extends JPanel {
	feneter()
	    {
		setPreferredSize(new Dimension(500, 500));
	    }
	    
	  public void paintComponent(Graphics g) {
		getWidth();
		getHeight();
        super.paintComponent(g);
	    }
	}
	
	@SuppressWarnings("serial")
	class MenuTexte extends JMenu implements ActionListener {
	    feneter feneter;
	    JMenuItem itemOuvrir = new JMenuItem("Ouvrir",KeyEvent.VK_O);
	    JMenuItem itemQuitter = new JMenuItem("Quitter", KeyEvent.VK_Q);
		MenuTexte(final feneter fen) {
		
		setText("Texte");
		this.feneter = fen;
		setMnemonic(KeyEvent.VK_C);
			

		itemOuvrir.setMnemonic(KeyEvent.VK_O);
		itemOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		itemOuvrir.addActionListener(this);
		add(itemOuvrir);
		
		/*raccorci*/
		itemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		itemQuitter.addActionListener(this);
		add(itemQuitter);
		
	    }
	    
	    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == itemOuvrir) 
			Ouvrir.createAndShowGUI();//ouvrir un text
		else if (source == itemQuitter) System.exit(0);
	    }
   }
	@SuppressWarnings("serial")
	class MenuDictionnaire extends JMenu implements ActionListener {
	    feneter feneter;
	    JMenuItem itemAspiration = new JMenuItem("Aspiration",KeyEvent.VK_A);
	    JMenuItem itemTran = new JMenuItem("Transformation  au  format  DELA", KeyEvent.VK_T);
	    JMenuItem itemQuitter = new JMenuItem("Quitter", KeyEvent.VK_Q);



	    MenuDictionnaire(final feneter fen) {
		
		setText("Dictionnaire");
		this.feneter = fen;
		setMnemonic(KeyEvent.VK_C);
			

		itemAspiration.setMnemonic(KeyEvent.VK_O);
		itemAspiration.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		itemAspiration.addActionListener(this);
		add(itemAspiration);
		
		itemTran.setMnemonic(KeyEvent.VK_T);
		itemTran.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
		itemTran.addActionListener(this);
		add(itemTran);
		
		
		itemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
		itemQuitter.addActionListener(this);
		add(itemQuitter);
		
	    }
	    
	    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == itemAspiration) {
		                                try {
		                                	Aspiration	tt=new Aspiration();
											tt.start();
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}       
									                                                                 
		                               }//Aspiration
		else if (source == itemTran)  {
			                            TransofmationDELA t=new TransofmationDELA();
			                               try {
											t.Tans();
										} catch (IOException e) {
											
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
			                         }//transifouramtion
		else if (source == itemQuitter) System.exit(0);
	    }
   }
	@SuppressWarnings("serial")
	class BarreMenu extends JMenuBar {
	    BarreMenu(feneter fen) {	
	    add(new MenuTexte(fen));
		add(new MenuDictionnaire(fen));
	    }
	}
	
	@SuppressWarnings("serial")
	public class Menu extends JFrame {
		Panneau p=new Panneau();
		Menu() throws IOException {
		feneter ardoise = new feneter();
		
		
		this.setTitle("Extraction d'information");
		
		JMenuBar barreMenu = new BarreMenu(ardoise);

		setJMenuBar(barreMenu);
				
        add(ardoise, BorderLayout.CENTER);
        getContentPane().add(p);
        setPreferredSize(new Dimension(650, 650));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocation(100, 100);
		pack();
		setVisible(true);
	    }

	}
	