package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.controller.LoginController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitubLogin {

  protected JFrame mFrame;

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  TwitubMainView twitubMainView;

  public TwitubLogin(IDatabase database, EntityManager entityManager, TwitubMainView twitubMainView) {
    this.mDatabase = database;
    this.mEntityManager = entityManager;
    this.twitubMainView = twitubMainView;
  }

  public JPanel show() {
    return initJPanel();
  }

  private JPanel initJPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(new java.awt.Color(240, 240, 255));

    JLabel pageLabel = new JLabel("Connexion");
    pageLabel.setBounds(100, 30, 200, 25);
    //center the text
    pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    pageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    panel.add(pageLabel);

    JLabel usernameLabel = new JLabel("Username");
    usernameLabel.setBounds(100, 70, 200, 25);
    panel.add(usernameLabel);

    JTextField usernameField = new JTextField();
    usernameField.setBounds(100, 100, 200, 25);
    panel.add(usernameField);

    JLabel passwordLabel = new JLabel("Password");
    passwordLabel.setBounds(100, 150, 200, 25);
    panel.add(passwordLabel);

    JPasswordField passwordField = new JPasswordField();
    passwordField.setBounds(100, 180, 200, 25);
    panel.add(passwordField);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(100, 230, 90, 25);
    loginButton.setBackground(new java.awt.Color(157, 153, 255));
    loginButton.setForeground(new java.awt.Color(255, 255, 255));
    panel.add(loginButton);

    LoginController loginController = new LoginController(mDatabase, mEntityManager, twitubMainView);
    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (loginController.login(usernameField.getText(), passwordField.getText())) {
          JOptionPane.showMessageDialog(mFrame, "Login successful");
          panel.setVisible(false);
          TwitubLogin.this.twitubMainView.isConnecte();
        } else {
          JOptionPane.showMessageDialog(mFrame, "Login failed");
        }
      }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBounds(210, 230, 90, 25);
    cancelButton.setBackground(new java.awt.Color(157, 153, 255));
    cancelButton.setForeground(new java.awt.Color(255, 255, 255));
    panel.add(cancelButton);
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        panel.setVisible(false);
        twitubMainView.loadTweetFilter("");
      }
    });

    return panel;
  }

}


