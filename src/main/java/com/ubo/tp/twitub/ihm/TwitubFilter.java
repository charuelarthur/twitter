package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.controller.TweetController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitubFilter {

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  TwitubMainView twitubMainView;

  public TwitubFilter(IDatabase database, EntityManager entityManager, TwitubMainView twitubMainView) {
    this.mDatabase = database;
    this.mEntityManager = entityManager;
    this.twitubMainView = twitubMainView;
  }

  public JPanel show() {
    return this.init();
  }

  private JPanel init() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    panel.add(new JLabel("Filter"), c);
    c.gridx = 1;
    c.gridy = 0;
    JTextField filterField = new JTextField();
    filterField.setColumns(20);
    panel.add(filterField, c);
    c.gridx = 0;
    c.gridy = 1;
    JButton filterButton = new JButton("Filter");
    TweetController tweetController = new TweetController((Database) mDatabase, mEntityManager);
    filterButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String filter = filterField.getText();
        twitubMainView.loadTweetFilter(filter);
      }
    });
    panel.add(filterButton, c);
    return panel;
  }
}
