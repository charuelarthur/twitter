package main.java.com.ubo.tp.twitub.core;

import java.io.File;
import java.util.HashSet;
import java.util.UUID;

import main.java.com.ubo.tp.twitub.datamodel.*;
import main.java.com.ubo.tp.twitub.events.file.IWatchableDirectory;
import main.java.com.ubo.tp.twitub.events.file.WatchableDirectory;
import main.java.com.ubo.tp.twitub.ihm.TwitubDirectory;
import main.java.com.ubo.tp.twitub.ihm.TwitubLogin;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;
import main.java.com.ubo.tp.twitub.ihm.TwitubMock;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitub {
	/**
	 * Base de données.
	 */
	protected IDatabase mDatabase;

	/**
	 * Gestionnaire des entités contenu de la base de données.
	 */
	protected EntityManager mEntityManager;

	/**
	 * Vue principale de l'application.
	 */
	protected TwitubMainView mMainView;

	/**
	 * Classe de surveillance de répertoire
	 */
	protected IWatchableDirectory mWatchableDirectory;

	protected IDatabaseObserver mDatabaseObserver;

	/**
	 * Répertoire d'échange de l'application.
	 */
	protected String mExchangeDirectoryPath;

	/**
	 * Idnique si le mode bouchoné est activé.
	 */
	protected boolean mIsMockEnabled = false;

	/**
	 * Nom de la classe de l'UI.
	 */
	protected String mUiClassName;

	/**
	 * Constructeur.
	 */
	public Twitub() {
		// Init du look and feel de l'application
		this.initLookAndFeel();

		// Initialisation de la base de données
		this.initDatabase();

		if (this.mIsMockEnabled) {
			// Initialisation du bouchon de travail
			this.initMock();
		} else {
			this.initView();
		}

		// Initialisation de l'IHM
	//	this.initGui();
	}

	private void initView() {
		TwitubDirectory twitubDirectory = new TwitubDirectory(this.mDatabase, this.mEntityManager);
		this.initDirectory(twitubDirectory.init());
		this.addAdmin();

		TwitubMainView twitubMainView = new TwitubMainView(this.mDatabase, this.mEntityManager);
		twitubMainView.show();
	}

	private void addAdmin() {
		User user = new User(UUID.randomUUID(), "admin", "admin", "admin", new HashSet<>(), "");
		this.mDatabase.addUser(user);
		this.mEntityManager.sendUser(user);
	}

	/**
	 * Initialisation du look and feel de l'application.
	 */
	protected void initLookAndFeel() {
	}

	/**
	 * Initialisation de l'interface graphique.
	 */
	protected void initGui() {
		// this.mMainView...
	}

	/**
	 * Initialisation du répertoire d'échange (depuis la conf ou depuis un file
	 * chooser). <br/>
	 * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de
	 * pouvoir utiliser l'application</b>
	 */
	/*protected void initDirectory() {
	}*/

	/**
	 * Indique si le fichier donné est valide pour servire de répertoire
	 * d'échange
	 * 
	 * @param directory
	 *            , Répertoire à tester.
	 */
	protected boolean isValideExchangeDirectory(File directory) {
		// Valide si répertoire disponible en lecture et écriture
		return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
				&& directory.canWrite();
	}

	/**
	 * Initialisation du mode bouchoné de l'application
	 */
	protected void initMock() {
		TwitubMock mock = new TwitubMock(this.mDatabase, this.mEntityManager);
		mock.showGUI();
	}

	/**
	 * Initialisation de la base de données
	 */
	protected void initDatabase() {
		mDatabase = new Database();
		mDatabaseObserver = new WatchableDatabase((Database) mDatabase);
		mEntityManager = new EntityManager(mDatabase);

		mDatabaseObserver.initWatching();
	}

	/**
	 * Initialisation du répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void initDirectory(String directoryPath) {
		mExchangeDirectoryPath = directoryPath;
		mWatchableDirectory = new WatchableDirectory(directoryPath);
		mEntityManager.setExchangeDirectory(directoryPath);

		mWatchableDirectory.initWatching();
		mWatchableDirectory.addObserver(mEntityManager);
	}

	public void show() {
		// ... setVisible?
	}
}
