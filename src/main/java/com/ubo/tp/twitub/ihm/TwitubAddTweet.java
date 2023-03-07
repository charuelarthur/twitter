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
    panel.setLayout(null);
    panel.setBackground(new java.awt.Color(240, 240, 255));

    JLabel pageLabel = new JLabel("Add a tweet");
    pageLabel.setBounds(100, 30, 200, 25);
    //center the text
    pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    pageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    panel.add(pageLabel);

    //add a text field
    JLabel nameLabel = new JLabel("Tweet");
    nameLabel.setBounds(100, 75, 200, 25);
    panel.add(nameLabel);

    JTextField textField = new JTextField();
    textField.setBounds(100, 100, 200, 30);
    panel.add(textField);

    JButton button = new JButton("Tweeter");
    button.setBounds(150, 150, 100, 25);
    button.setBackground(new java.awt.Color(157, 153, 255));
    button.setForeground(new java.awt.Color(255, 255, 255));
    TweetController tweetController = new TweetController((Database) mDatabase, mEntityManager);
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(textField.getText().length() > 250) {
          JOptionPane.showMessageDialog(null, "Tweet is too long");
        } else if (tweetController.addTweet(textField.getText())) {
          JOptionPane.showMessageDialog(null, "Tweet added");
          panel.setVisible(false);
          twitubMainView.loadTweetFilter("");
        } else {
          JOptionPane.showMessageDialog(null, "Tweet not added");
        }
      }
    });
    panel.add(button);
    return panel;
  }
}
