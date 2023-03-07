package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.controller.TweetController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.lang.Math;


/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView {

  protected JFrame frame;

  protected JPanel panelMenu;
  protected JPanel panelTweet;
  protected JPanel panelFilter;
  protected JPanel panelProfile;

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
    this.panelFilter = new JPanel();
    this.panelProfile = new JPanel();
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
    frame.setVisible(true);
    this.init();
  }

  private void init() {
    panelMenu.setLayout(new BorderLayout());
    menuBar.add(menuNonCo());
    GridBagConstraints c = new GridBagConstraints();
    panelMenu.add(menuBar, BorderLayout.NORTH);
    frame.setContentPane(panelMenu);
    this.loadTweetFilter("");
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
    item = new JMenuItem("Home");
    item.addActionListener(e -> {
      this.frame.remove(this.panelProfile);
      this.loadTweetFilter("");
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
    item = new JMenuItem("Home");
    item.addActionListener(e -> {
      this.removePanel();
      this.panelProfile.removeAll();
      this.panelProfile.setVisible(false);
      this.loadTweetFilter("");
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Add a tweet");
    item.addActionListener(e -> {
      this.frame.remove(this.panelTweet);
      this.frame.add(new TwitubAddTweet(mDatabase, mEntityManager, this).show());
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Profile");
    item.addActionListener(e -> {
      this.panelProfile.removeAll();
      this.removePanel();
      panelProfile.add(new TwitubProfile(mDatabase, mEntityManager, this, this.mEntityManager.getCurrentUser()).show(), BorderLayout.PAGE_START);
      this.panelProfile.setVisible(true);
      this.frame.add(panelProfile);
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
    this.loadTweetFilter("");
    this.frame.setVisible(true);
  }

  public void isConnecte() {
    this.menuBar.removeAll();
    this.menuBar.add(menuCo());
    this.loadTweetFilter("");
    this.frame.setVisible(true);
  }

  public void loadTweetFilter(String filter) {
    TweetController tweetController = new TweetController((Database) mDatabase, mEntityManager);
    //this.frame.remove(this.panelTweet);
    panelTweet.removeAll();
    this.frame.add(panelTweet);
    panelTweet.setVisible(false);
    panelTweet.setLayout(new BorderLayout());
    //
    Set<Twit> twits = tweetController.filterTweet(filter);
    //sort by emission date
    List<Twit> twitsList = new ArrayList<>(twits);
    Collections.sort(twitsList, new Comparator<Twit>() {
              @Override
              public int compare(Twit o1, Twit o2) {
                long date1 = o1.getEmissionDate();
                long date2 = o2.getEmissionDate();
                return Long.compare(date2, date1);
              }
            }
    );

    panelTweet.add(new TwitubTwitList(mDatabase, mEntityManager, this).show(twitsList), BorderLayout.NORTH);
    panelTweet.add(new TwitubFilter(mDatabase, mEntityManager, this).show(), BorderLayout.SOUTH);
    this.frame.add(panelTweet);
    panelTweet.setVisible(true);
    this.frame.setVisible(true);
  }

  public void loadProfile(User user) {
    panelProfile.removeAll();
    this.frame.remove(panelTweet);
    this.frame.remove(panelFilter);
    this.panelProfile.add(new TwitubProfile(mDatabase, mEntityManager, this, user).show());
    this.frame.add(panelProfile);
    panelProfile.setVisible(true);
    this.frame.setVisible(true);
  }

  public void removePanel() {
    this.frame.remove(panelProfile);
    this.frame.remove(panelTweet);
    this.frame.remove(panelFilter);
    this.frame.setVisible(true);
  }

}
