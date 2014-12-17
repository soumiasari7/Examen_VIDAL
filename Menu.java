package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;


	
	class feneter extends JPanel {
	    
		feneter()
	    {
		setPreferredSize(new Dimension(500, 500));
		setBackground(Color.BLUE);//insertion de limage
		setForeground(Color.YELLOW);
	    }
	    
	    public void paintComponent(Graphics g) {
		int largeur = getWidth();
		int hauteur = getHeight();
		
		super.paintComponent(g);
	    }
	}
	
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
		if (source == itemOuvrir) feneter.setForeground(Color.RED);//ouvrir un text
		else if (source == itemQuitter) System.exit(0);
	    }
   }
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
		if (source == itemAspiration) feneter.setBackground(Color.RED);//Aspiration
		else if (source == itemTran)  feneter.setBackground(Color.PINK);//transifouramtion
		else if (source == itemQuitter) System.exit(0);
	    }
   }
	class BarreMenu extends JMenuBar {
	    BarreMenu(feneter fen) {	
	    add(new MenuTexte(fen));
		add(new MenuDictionnaire(fen));
	    }
	}
	
	public class Menu extends JFrame {
	    Menu() throws IOException {
		feneter ardoise = new feneter();
		this.setTitle("Extraction d'information");
		
		JMenuBar barreMenu = new BarreMenu(ardoise);

		setJMenuBar(barreMenu);		
		add(ardoise, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(100, 100);
		pack();
		setVisible(true);
	    }
	}
	