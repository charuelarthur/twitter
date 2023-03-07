package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.controller.LoginController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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

  private JPanel initJPanel()  {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    panel.add(new JLabel("Username"), c);
    c.gridx = 1;
    c.gridy = 0;
    JTextField usernameField = new JTextField();
    usernameField.setColumns(20);
    panel.add(usernameField, c);
    c.gridx = 0;
    c.gridy = 1;
    panel.add(new JLabel("Password"), c);
    c.gridx = 1;
    c.gridy = 1;
    JPasswordField passwordField = new JPasswordField();
    passwordField.setColumns(20);
    panel.add(passwordField, c);
    c.gridx = 0;
    c.gridy = 2;
    JButton loginButton = new JButton("Login");

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
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        panel.setVisible(false);
        twitubMainView.loadTweetFilter("");
      }
    });

    panel.add(loginButton, c);
    c.gridx = 1;
    c.gridy = 2;
    panel.add(cancelButton, c);

    return panel;
  }
}
