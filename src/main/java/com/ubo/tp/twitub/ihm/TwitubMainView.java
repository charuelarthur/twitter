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


/**
 * Classe de la vue principale de l'application.
 */
public class TwitubMainView {

  protected JFrame frame;

  protected JPanel panelMenu;
  protected JPanel panelTweet;
  protected JPanel panelFilter;
  protected JPanel panelLogin;
  protected JPanel panelRegister;
  protected JScrollPane panelProfile;

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  protected Component login;
  protected Component register;
  protected Component twit;
  protected Component profile;

  protected JMenuBar menuBar;

  public TwitubMainView(IDatabase mDatabase, EntityManager mEntityManager) {
    this.mDatabase = mDatabase;
    this.mEntityManager = mEntityManager;
    this.frame = new JFrame();
    this.panelMenu = new JPanel();
    this.panelTweet = new JPanel();
    this.panelFilter = new JPanel();
    this.panelLogin = new JPanel();
    this.panelRegister = new JPanel();
    this.panelProfile = new JScrollPane();
    this.login = new TwitubLogin(mDatabase, mEntityManager, this).show();
    this.register = new TwitubCreateUser(mDatabase, mEntityManager, this).show();
    this.twit = new TwitubAddTweet(mDatabase, mEntityManager, this).show();
    this.menuBar = new JMenuBar();
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
    panelMenu.add(menuBar, BorderLayout.NORTH);
    frame.setContentPane(panelMenu);
    this.loadTweetFilter("");
    frame.setVisible(true);
  }


  private JMenu menuNonCo() {
    JMenu menu = new JMenu("Menu");
    JMenuItem item = new JMenuItem("Login");
    item.addActionListener(e -> {
      this.frame.setVisible(false);
      this.removePanel();
      this.login.setVisible(true);
      this.frame.add(login);
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Register");
    item.addActionListener(e -> {
      this.frame.setVisible(false);
      this.removePanel();
      this.register.setVisible(true);
      this.frame.add(register);
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Home");
    item.addActionListener(e -> {
      this.frame.setVisible(false);
      this.removePanel();
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
    this.profile = new TwitubProfile(mDatabase, mEntityManager, this, this.mEntityManager.getCurrentUser()).show();
    JMenu menu = new JMenu("Menu");
    JMenuItem item = new JMenuItem("Logout");
    item.addActionListener(e -> {
      this.isDisconnect();
    });
    menu.add(item);
    item = new JMenuItem("Home");
    item.addActionListener(e -> {
      this.frame.setVisible(false);
      this.removePanelConnecte();
      this.panelProfile.removeAll();
      this.panelProfile.setVisible(false);
      this.loadTweetFilter("");
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Add a tweet");
    item.addActionListener(e -> {
      this.frame.setVisible(false);
      this.frame.remove(this.panelTweet);
      this.removePanelConnecte();
      this.twit.setVisible(true);
      this.frame.add(twit);
      this.frame.setVisible(true);
    });
    menu.add(item);
    item = new JMenuItem("Profile");
    item.addActionListener(e -> {
      this.frame.setVisible(false);
      this.panelProfile.removeAll();
      this.removePanelConnecte();
      this.profile.setVisible(true);
      this.frame.add(profile);
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
    //add scroll
    JScrollPane scrollBar = new JScrollPane(new TwitubTwitList(mDatabase, mEntityManager, this).show(twitsList));
    scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    panelTweet.add(scrollBar, BorderLayout.CENTER);
    panelTweet.add(new TwitubFilter(mDatabase, mEntityManager, this).show(), BorderLayout.SOUTH);
    this.frame.add(panelTweet);
    panelTweet.setVisible(true);
    this.frame.setVisible(true);
  }

  public void loadProfile(User user) {
    this.panelProfile.removeAll();
    this.panelTweet.setVisible(false);
    this.frame.remove(panelTweet);
    this.frame.remove(panelFilter);
    panelProfile = new JScrollPane(new TwitubProfile(mDatabase, mEntityManager, this, user).show());
    panelProfile.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    panelProfile.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    panelTweet.add(panelProfile, BorderLayout.CENTER);
    this.frame.add(this.panelProfile);
    this.panelProfile.setVisible(true);
    this.frame.setVisible(true);
  }

  public void removePanel() {
    this.frame.remove(panelProfile);
    this.frame.remove(panelTweet);
    this.frame.remove(panelFilter);
    this.frame.remove(login);
    this.frame.remove(register);
  }

  public void removePanelConnecte() {
    this.frame.remove(panelTweet);
    this.frame.remove(twit);
    this.frame.remove(profile);
  }

}
