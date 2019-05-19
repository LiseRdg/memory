package memory.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import memory.om.Jeu;
import memory.om.Reponse;

public class FrameMemory extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1720719442489813605L;
	// private DialogDebut dialogueDebut = new DialogDebut();
	private JPanel panelGrid = new JPanel();
	private JPanel panelResultat = new JPanel();
	private JPanel panelJeu = new JPanel();
	private JPanel panelCarteTrouvee = new JPanel();
	private JPanel panelScore = new JPanel();
	private JTextField texteScore = new JTextField();

	private int score = 0;

	private GridLayout grid = new GridLayout(4, 4);

	private Jeu nouveauJeu;

	Font police = new Font("Monaco", Font.PLAIN, 18);

	static final String ACTION_QUITTER = "quitter";
	static final String ACTION_REGLES = "règles du jeu";
	static final String ACTION_NOUVPARTIE = "nouvelle partie";
	static final String ACTION_MENU = "menu";
	static final String ACTION_CHOIX = "sélection d'une carte";
	static final String ACTION_TIMER = "temps";

	private ArrayList<JButton> listeBut = new ArrayList<JButton>();
	private ArrayList<JButton> listeCarteTrouvee = new ArrayList<JButton>();
	private int nbCarte = 16;
	private int indiceCarteChoisie, varIndex, nouvPartie = 0, indiceGagnant, color = 94;

	JOptionPane popupNouvPartie = new JOptionPane();

	// timer
	Timer timer = new Timer(0000, (e) -> {
		try {
			this.listeBut.get(this.indiceCarteChoisie).setText("");
			this.listeBut.get(this.varIndex).setText("");
			this.listeBut.get(this.varIndex).setIcon(null);

			this.listeBut.get(this.indiceCarteChoisie).setIcon(null);
			this.listeBut.get(this.indiceCarteChoisie).revalidate();

			Thread.sleep(1000);
		} catch (Exception InterruptedException) {
		}
	});

	private JButton but = new JButton("mode triche");

	public FrameMemory() {
		super();
		this.setTitle("Memory");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.nouveauJeu = new Jeu(nbCarte / 2);

		// demarrer avec une JDialog
		// this.dialogueDebut.setVisible(true);
		// this.dialogueDebut.setModal(true);

		// ajout des panels
		// contentPane
		this.setLayout(new BorderLayout());
		this.add(panelGrid);
		this.add(panelResultat);
		this.add(panelGrid, BorderLayout.CENTER);
		this.add(panelResultat, BorderLayout.WEST);
		this.panelGrid.setBackground(Color.DARK_GRAY);

		// bordures
		this.panelGrid.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.panelScore.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.panelResultat.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		this.panelResultat.setBorder(BorderFactory.createTitledBorder("SCORE"));
		this.panelGrid.setBorder(BorderFactory.createTitledBorder("JEU DE CARTE"));
		this.panelCarteTrouvee.setBorder(BorderFactory.createTitledBorder("CARTES TROUVEES"));

		// gap entre les cartes
		grid.setHgap(30);
		grid.setVgap(30);

		// PanelGrid
		panelGrid.setLayout(new BorderLayout());
		panelGrid.add(panelJeu);
		panelGrid.add(panelCarteTrouvee);
		panelGrid.add(panelJeu, BorderLayout.CENTER);
		panelGrid.add(panelCarteTrouvee, BorderLayout.SOUTH);

		// panelGrid - panelJeu
		panelJeu.setLayout(grid);

		// panelGrid - panelCarteTrouvee
		panelCarteTrouvee.setLayout(new FlowLayout());
		for (int i = 0; i < this.nbCarte / 2; i++) {
			this.listeCarteTrouvee.add(new JButton());
			this.listeCarteTrouvee.get(i).setActionCommand(ACTION_CHOIX);
			this.panelCarteTrouvee.add(this.listeCarteTrouvee.get(i));
			this.indiceCarteChoisie = i;
			this.listeCarteTrouvee.get(i).setOpaque(false);
			this.listeCarteTrouvee.get(i).setContentAreaFilled(false); // On met à false pour empêcher le composant de
			// peindre l'intérieur du JButton.
			this.listeCarteTrouvee.get(i).setBorderPainted(true); // De même, on ne veut pas afficher les bordures.
			this.listeCarteTrouvee.get(i).setFocusPainted(false);
		}

		// PanelResultat
		// panelScore
		panelResultat.add(panelScore);
		panelScore.setBorder(BorderFactory.createTitledBorder("Score"));
		this.texteScore.setText(score + "");
		// panelScore.setBackground(Color.WHITE);
		this.texteScore.setHorizontalAlignment(JTextField.CENTER);
		this.texteScore.setEditable(false);
		panelScore.add(texteScore);
		this.texteScore.setHorizontalAlignment(JTextField.CENTER);
		this.texteScore.setEditable(false);

		// création du panelGrid
		// création de l'ArrayList de buttons
		for (int i = 0; i < nbCarte; i++) {
			this.listeBut.add(new JButton());
			this.listeBut.get(i).setActionCommand(ACTION_CHOIX);
			this.listeBut.get(i).addActionListener(this);
			this.panelJeu.add(this.listeBut.get(i));
			this.indiceCarteChoisie = i;
			System.out.println(this.listeBut.get(i));
			this.listeBut.get(i).setContentAreaFilled(false);
			this.listeBut.get(i).setBorderPainted(true);
			this.listeBut.get(i).setFocusPainted(false);
			this.listeBut.get(i).setOpaque(true);
			this.listeBut.get(i).setBackground(Color.RED);
			// this.listeBut.get(i).setBackground(new java.awt.Color(255, 94, 77));
			// this.color++;

		}

		// JMenuBar;
		JMenuBar menuBar = new JMenuBar();
		JMenu actionsMenu = new JMenu("Action");
		JMenu itemAide = new JMenu("?");
		JMenuItem itemNouvPartie = new JMenuItem("Nouvelle partie");
		JMenuItem itemRegles = new JMenuItem("Règles du jeu");
		JMenuItem itemMenu = new JMenuItem("Menu");
		JMenuItem itemQuitter = new JMenuItem("Quitter");
		this.setJMenuBar(menuBar);
		menuBar.add(actionsMenu);
		menuBar.add(itemAide);
		itemRegles.addActionListener(e -> regles());

		itemNouvPartie.setActionCommand(ACTION_NOUVPARTIE);
		itemQuitter.setActionCommand(ACTION_QUITTER);
		itemRegles.setActionCommand(ACTION_REGLES);
		itemMenu.setActionCommand(ACTION_MENU);

		itemQuitter.addActionListener(e -> quitter());
		itemNouvPartie.addActionListener(e -> nouvellePartie());

		actionsMenu.add(itemMenu);
		actionsMenu.addSeparator();
		actionsMenu.add(itemNouvPartie);
		actionsMenu.addSeparator();
		actionsMenu.add(itemQuitter);
		actionsMenu.addSeparator();
		itemAide.add(itemRegles);

		this.panelResultat.add(but);
		this.but.addActionListener(this);

		// taille des panels
		this.panelResultat.setSize(200, 100);
		this.setSize(1200, 800);
		// this.pack();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				quitter();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.but) {
			modeTriche();
		} else if (ACTION_CHOIX.equals(e.getActionCommand())) {
			this.indiceCarteChoisie = this.listeBut.indexOf(e.getSource());

			Onclick("Tribal-Cymbal-0121.wav");

			Reponse reponse = this.nouveauJeu.jouer(this.indiceCarteChoisie);

			System.out.println(this.nouveauJeu.getCarteValeur(indiceCarteChoisie));

			if (reponse.equals(Reponse.PREMIERE)) {

				Image img = null;
				try {
					img = ImageIO.read(new FileInputStream("ah.PNG"));// la dans le constructeur tu fais un truc genre"leNom_"+this.nouveaujEu.getCarteValeur(this.indiceCaeteChoisie)
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// this.listeBut.get(i).setIcon(new ImageIcon(img));
				this.listeBut.get(this.indiceCarteChoisie).setIcon(new ImageIcon(img));
				// .setText(nouveauJeu.getCarteValeur(this.indiceCarteChoisie) + "");
				this.varIndex = this.indiceCarteChoisie;
				this.texteScore.setText(score + "");
			} else if (reponse.equals(Reponse.PERDU)) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(new FileInputStream("ah.PNG"));// la dans le constructeur tu fais un truc genre"leNom_"+this.nouveaujEu.getCarteValeur(this.indiceCaeteChoisie)
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// this.listeBut.get(i).setIcon(new ImageIcon(img));
				this.listeBut.get(this.indiceCarteChoisie).setIcon(new ImageIcon(img));
				this.listeBut.get(this.indiceCarteChoisie);
				//.setText(this.nouveauJeu.getCarteValeur(this.indiceCarteChoisie) + "");
				this.timer.setRepeats(false);
				this.timer.start();
				this.score++;
				this.texteScore.setText(score + "");
			} else if (reponse.equals(Reponse.GAGNE)) {
				this.listeBut.get(indiceCarteChoisie)
				.setText(this.nouveauJeu.getCarteValeur(this.indiceCarteChoisie) + "");
				this.listeBut.get(indiceCarteChoisie).setEnabled(false);
				this.listeBut.get(varIndex).setEnabled(false);
				this.score++;
				this.nouvPartie++;
				this.indiceGagnant = indiceCarteChoisie;
				this.texteScore.setText(score + "");
				// panelCarteTrouvee
				this.listeCarteTrouvee.get(indiceGagnant / 2).setText(indiceCarteChoisie / 2 + "");

				// si la partie est finie
				if (this.nouveauJeu.isPartieTerminee() == true) {
					JOptionPane.showMessageDialog(this,
							"<html><h4>Partie terminée ! </h4><br/>Voulez vous rejouer ? <br/></html>",
							"Partie terminée", JOptionPane.INFORMATION_MESSAGE);
					nouvellePartie();
				}
			}
		}
	}

	public void debut(String niveau, int nbJoueur, int nbCarte) {

	}

	public void regles() {
		JOptionPane.showMessageDialog(this,
				"<html><h2> Règles du Jeu : </h2><br/><h4>Un joueur </h4><br/> Le but est de trouver une à une toutes les paires du jeu avec le moins d'essais possibles. <br/>Le joueur choisi deux cartes :<br/><li>si elles sont identiques, c'est une paire</li><li>sinon les cartes se retournent et le joueur fait un nouveau choix</li> <gr/><br/>Le premier joueur retourne 2 cartes de son choix.<h4>Plusieurs joueurs</h4><br/><li>Si les cartes sont identiques, le joueur les conserve à côté de lui et rejoue.</li><li>Si les cartes ne sont pas identiques, le joueur les retourne face cachée de nouveau. C’est alors au joueur suivant de jouer.</li><br/>Le jeu de termine une fois que toutes les paires ont été trouvées.<br/>Le joueur vainqueur est celui qui possède le plus de paires.</html>");
	}

	void quitter() {
		int reponse = JOptionPane.showConfirmDialog(this, "Voulez vous quitter ?");
		if (reponse == JOptionPane.YES_NO_OPTION) {
			dispose();
		}
	}

	void modeTriche() {
		this.nouveauJeu = new Jeu(nbCarte / 2, true);
	}

	void nouvellePartie() {

		this.texteScore.setText("0");
		this.score = 0;
		for (int i = 0; i < nbCarte; i++) {
			this.listeBut.get(i).setText("");
			this.listeBut.get(i).setIcon(null);
			this.listeBut.get(i).setEnabled(true);

		}
		this.nouveauJeu = new Jeu(nbCarte / 2);

	}

	void changerNombreCarte() {
		this.nouveauJeu = new Jeu(this.nbCarte / 2);
	}

	public void Onclick(String url) {

		AudioInputStream streamAudio = null;
		try {
			streamAudio = AudioSystem.getAudioInputStream(new File(url).getAbsoluteFile());
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(streamAudio);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();

	}

}

// pour changer nb de carte il faut creer un nouveau jeu a chaque fois

// ce qu'il faut ajouter
// mise en page de la Jdialog
// une image sur chaque button/carte
// du son ?
// modifier la mise en page du score
// mettre des img à la place des nombre quand on retourne les cartes
// la couleur "sous les carte" dans le gap
