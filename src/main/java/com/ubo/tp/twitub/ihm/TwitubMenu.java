package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

public class TwitubMenu {

  protected JFrame mFrame;

  protected IDatabase mDatabase;
  private EntityManager mEntityManager;

  public TwitubMenu(IDatabase database, EntityManager entityManager) {
    this.mDatabase = database;
    this.mFrame = new JFrame();
    this.mFrame.setTitle("Twitub Menu");
    this.mFrame.setSize(400, 400);
    this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.mFrame.setVisible(true);
    this.mEntityManager = entityManager;
  }

  public void show() {
    this.mFrame.setVisible(true);
    this.init();
  }

  private void init() {
    //add icon to the frame
    ImageIcon image = new ImageIcon("src/main/resources/images/logo_20.jpg");
    mFrame.setIconImage(image.getImage());
    mFrame.setLayout(new GridBagLayout());

    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu menuHelp = new JMenu("?");
    menuBar.add(menuFile);
    JMenuItem menuExit = new JMenuItem("Quitter");
    ImageIcon imageExit = new ImageIcon("src/main/resources/images/exitIcon_20.png");
    menuExit.setIcon(imageExit);
    menuFile.add(menuExit);

    menuExit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });


    menuBar.add(menuHelp);
    JMenuItem menuAbout = new JMenuItem("A propos");
    menuHelp.add(menuAbout);

    menuAbout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame jFrameHelp = new JFrame("A propos");
        jFrameHelp.setLayout(new GridBagLayout());

        //ajout d'une image
        ImageIcon image = new ImageIcon("src/main/resources/images/logo_50.jpg");
        JLabel label = new JLabel(image);
        jFrameHelp.add(label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));

        JTextArea jTextAreaHelp = new JTextArea();
        jTextAreaHelp.setText("UBO M2-TIIL \n Département Informatique");
        jTextAreaHelp.setEditable(false);
        jFrameHelp.add(jTextAreaHelp, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));

        JButton jButtonClose = new JButton("Ok");
        jButtonClose.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            jFrameHelp.dispose();
          }
        });
        jFrameHelp.add(jButtonClose, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));

        jFrameHelp.pack();
        jFrameHelp.setVisible(true);
      }
    });

    //
    // Gestion de la base de données

    JLabel dbLabel = new JLabel("Database");

    Button addUserButton = new Button("Add User (MOCK)");
    addUserButton.setPreferredSize(new Dimension(100, 50));
    addUserButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        TwitubMenu.this.addUserInDatabase();
      }
    });

    Button addTwitButton = new Button("Add Twit (MOCK)");
    addTwitButton.setPreferredSize(new Dimension(100, 50));
    addTwitButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        TwitubMenu.this.addTwitInDatabase();
      }
    });

    //
    // Gestion des fichiers

    JLabel fileLabel = new JLabel("Files");

    Button sendUserButton = new Button("Send User");
    sendUserButton.setPreferredSize(new Dimension(100, 50));
    sendUserButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        TwitubMenu.this.sendUser();
      }
    });

    Button sendTwitButton = new Button("Send Twit");
    sendTwitButton.setPreferredSize(new Dimension(100, 50));
    sendTwitButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        TwitubMenu.this.sendTwit();
      }
    });

    Button sendCreateUserButton = new Button("Create User");
    sendCreateUserButton.setPreferredSize(new Dimension(100, 50));
  /*  sendCreateUserButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        TwitubCreateUser twitubCreateUser = new TwitubCreateUser(mDatabase, mEntityManager);
        twitubCreateUser.show();
      }
    });*/


    mFrame.setJMenuBar(menuBar);

    mFrame.add(dbLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    mFrame.add(addUserButton, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.WEST,
            GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    mFrame.add(addTwitButton, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.EAST,
            GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    mFrame.add(fileLabel, new GridBagConstraints(0, 2, 2, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(15, 5, 0, 5), 0, 0));
    mFrame.add(sendUserButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.WEST,
            GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    mFrame.add(sendTwitButton, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.EAST,
            GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
    mFrame.add(sendCreateUserButton, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
  }

  /**
   * Ajoute un twit fictif à la base de données.
   */
  protected void addTwitInDatabase() {
    // Création 'un twit fictif
    Twit newTwit = this.generateTwit();

    // Ajout du twit
    this.mDatabase.addTwit(newTwit);
  }

  /**
   * Génération d'un twit fictif.
   */
  protected Twit generateTwit() {
    // Si la base n'a pas d'utilisateur
    if (this.mDatabase.getUsers().size() == 0) {
      // Création d'un utilisateur
      this.addUserInDatabase();
    }

    // Récupération d'un utilisateur au hazard
    int userIndex = new Random().nextInt(this.mDatabase.getUsers().size());
    User randomUser = new ArrayList<User>(this.mDatabase.getUsers()).get(Math.max(0, userIndex - 1));

    // Création d'un twit fictif
    Twit newTwit = new Twit(randomUser, "Twit fictif!! #Mock #test ;)");

    return newTwit;
  }

  /**
   * Ajoute un utilisateur fictif à la base de donnée.
   */
  protected void addUserInDatabase() {
    // Création d'un utilisateur fictif
    User newUser = this.generateUser();
    // Ajout de l'utilisateur à la base
    this.mDatabase.addUser(newUser);
  }

  /**
   * Génération d'un utilisateur fictif.
   */
  protected User generateUser() {
    int randomInt = new Random().nextInt(99999);
    String userName = "MockUser" + randomInt;

    return new User(UUID.randomUUID(), userName, "--", userName, new HashSet<>(), "");
  }
  /**
   * Génération et envoi d'un fichier utilisateur
   */
  protected void sendUser() {
    // Création d'un utilisateur fictif
    User newUser = this.generateUser();

    // Génération du fichier utilisateur
    this.mEntityManager.sendUser(newUser);
  }

  /**
   * Génération et envoi d'un fichier twit
   */
  protected void sendTwit() {
    // Création d'un twit fictif
    Twit newTwit = this.generateTwit();

    // Génération du fichier twit
    this.mEntityManager.sendTwit(newTwit);
  }


}
