package main.java.com.ubo.tp.twitub.ihm;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

import main.java.com.ubo.tp.twitub.controller.LoginController;
import main.java.com.ubo.tp.twitub.controller.UserController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

public class TwitubMock {

	/**
	 * Fenetre du bouchon
	 */
	protected JFrame mFrame;

	/**
	 * Base de donénes de l'application.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire de bdd et de fichier.
	 */
	protected EntityManager mEntityManager;
	private String mSavePath;

	/**
	 * Constructeur.
	 * 
	 * @param database
	 *            , Base de données de l'application.
	 */
	public TwitubMock(IDatabase database, EntityManager entityManager) {
		this.mDatabase = database;
		this.mEntityManager = entityManager;
	}

	/**
	 * Lance l'afficahge de l'IHM.
	 */
	public void showGUI() {
		// Init auto de l'IHM au cas ou ;)
		if (mFrame == null) {
			this.initGUI();
		}

		// Affichage dans l'EDT
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Custom de l'affichage
				JFrame frame = TwitubMock.this.mFrame;
				Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation((screenSize.width - frame.getWidth()) / 6,
						(screenSize.height - frame.getHeight()) / 4);

				// Affichage
				TwitubMock.this.mFrame.setVisible(true);

				TwitubMock.this.mFrame.pack();
			}
		});
	}

	/**
	 * Initialisation de l'IHM
	 */
	protected void initGUI() {
		this.loginView();
		//this.selectSavePath();
	}

	protected void menuView() {
		// Création de la fenetre principale


	}

	/**
	 * Fenetre pour selectionner le chemin du fichier de sauvegarde au demarrage de l'application
	 */
	protected void selectSavePath() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnVal = fileChooser.showOpenDialog(this.mFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.mSavePath = fileChooser.getSelectedFile().getAbsolutePath();
			this.mEntityManager.setExchangeDirectory(this.mSavePath);
		} else {
			System.exit(0);
		}
	}

	/**
	 * Login view with username and password
	 */
	protected void loginView() {
		//init admin user
		this.addUserAdmin();


	}



	/**
	 * Ajoute un admin
	 */
	protected void addUserAdmin() {
		// Création d'un utilisateur fictif
		User newUser = this.userAdmin();
		// Ajout de l'utilisateur à la base
		this.mDatabase.addUser(newUser);
	}





	protected User userAdmin() {
		return new User(UUID.randomUUID(), "admin", "admin", "admin", new HashSet<>(), "");
	}




}
