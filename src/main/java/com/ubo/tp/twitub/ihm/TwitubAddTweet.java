package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.controller.TweetController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitubAddTweet {

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  TwitubMainView twitubMainView;
  public TwitubAddTweet(IDatabase mDatabase, EntityManager mEntityManager, TwitubMainView twitubMainView) {
    this.mDatabase = mDatabase;
    this.mEntityManager = mEntityManager;
    this.twitubMainView = twitubMainView;
  }

  public JPanel show() {
    return this.initJPanel();
  }

  private JPanel initJPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;

    //add a text field
    JLabel nameLabel = new JLabel("Tweet:");
    JTextField textField = new JTextField();
    //textfield for a max of 250 characters
    textField.setColumns(20);
    panel.add(nameLabel, c);
    c.gridx = 1;
    c.gridy = 0;
    panel.add(textField, c);

//add a button
    JButton button = new JButton("Add Tweet");

    TweetController tweetController = new TweetController((Database) mDatabase, mEntityManager);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(textField.getText().length() > 250) {
          JOptionPane.showMessageDialog(null, "Tweet is too long");
        } else if (tweetController.addTweet(textField.getText())) {
          JOptionPane.showMessageDialog(null, "Tweet added");
          panel.setVisible(false);
          twitubMainView.loadTweet();
        } else {
          JOptionPane.showMessageDialog(null, "Tweet not added");
        }
      }
    });
    c.gridx = 0;
    c.gridy = 1;
    panel.add(button, c);
    return panel;
  }
}
