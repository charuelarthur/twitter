package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView {

  protected JFrame frame;

  protected JPanel panelMenu;
  protected JPanel panelTweet;

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  protected TwitubLogin twitubLogin;

  protected TwitubCreateUser twitubCreateUser;

  protected JMenuBar menuBar;

  public TwitubMainView(IDatabase mDatabase, EntityManager mEntityManager) {
    this.mDatabase = mDatabase;
    this.mEntityManager = mEntityManager;
    this.frame = new JFrame();
    this.panelMenu = new JPanel();
    this.panelTweet = new JPanel();
    this.twitubLogin = new TwitubLogin(mDatabase, mEntityManager, this);
    this.menuBar = new JMenuBar();
    this.twitubCreateUser = new TwitubCreateUser(mDatabase, mEntityManager, this);
  }

  public void show() {
    frame.setTitle("Twitub");
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setVisible(true);this.init();
  }

  private void init() {
    panelMenu.setLayout(new BorderLayout());
    menuBar.add(menuNonCo());
    panelMenu.add(menuBar, BorderLayout.NORTH);
    frame.setContentPane(panelMenu);
    this.loadTweet();
    frame.setVisible(true);
  }


  private JMenu menuNonCo() {
    JMenu menu = new JMenu("Menu");
    JMenuItem item = new JMenuItem("Login");
    item.addActionListener(e -> {
      this.frame.remove(this.panelTweet);
      this.frame.add(this.twitubLogin.show());
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Register");
    item.addActionListener(e -> {
      this.frame.remove(this.panelTweet);
      this.frame.add(this.twitubCreateUser.show());
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Exit");
    item.addActionListener(e -> {
      System.exit(0);
    });
    menu.add(item);
    return menu;
  }

  private JMenu menuCo() {
    JMenu menu = new JMenu("Menu");
    JMenuItem item = new JMenuItem("Logout");
    item.addActionListener(e -> {
      this.isDisconnect();
    });
    menu.add(item);
    item = new JMenuItem("Add a tweet");
    item.addActionListener(e -> {
      this.frame.remove(this.panelTweet);
      this.frame.add(new TwitubAddTweet(mDatabase, mEntityManager, this).show());
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Exit");
    item.addActionListener(e -> {
      System.exit(0);
    });
    menu.add(item);
    return menu;
  }

  private void isDisconnect() {
    mEntityManager.setCurrentUser(null);
    this.menuBar.removeAll();
    this.menuBar.add(menuNonCo());
    this.loadTweet();
    this.frame.setVisible(true);
  }

  public void isConnecte() {
    this.menuBar.removeAll();
    this.menuBar.add(menuCo());
    this.loadTweet();
    this.frame.setVisible(true);
  }

  public void loadTweet() {
    panelTweet.removeAll();
    panelTweet.setLayout(new BorderLayout());
    panelTweet.add(new TwitubTwitList(mDatabase, mEntityManager, this).show(mDatabase.getTwits()), BorderLayout.CENTER);
    this.frame.add(panelTweet);
    this.frame.setVisible(true);
  }
}
