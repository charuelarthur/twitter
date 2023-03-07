package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.controller.LoginController;
import main.java.com.ubo.tp.twitub.controller.UserController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwitubCreateUser {

  protected JFrame mFrame;

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  TwitubMainView twitubMainView;


  public TwitubCreateUser(IDatabase mDatabase, EntityManager mEntityManager, TwitubMainView twitubMainView) {
    this.mDatabase = mDatabase;
    this.mEntityManager = mEntityManager;
    this.twitubMainView = twitubMainView;
  }

  public JPanel show() {
    return initJPanel();
  }

  private JPanel initJPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(new java.awt.Color(240, 240, 255));

    JLabel pageLabel = new JLabel("Register");
    pageLabel.setBounds(100, 30, 200, 25);
    //center the text
    pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    pageLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    panel.add(pageLabel);

    JLabel nameLabel = new JLabel("Name*");
    nameLabel.setBounds(100, 60, 200, 25);
    panel.add(nameLabel);

    JTextField nameField = new JTextField();
    nameField.setBounds(100, 85, 200, 25);
    panel.add(nameField);

    JLabel passwordLabel = new JLabel("Password*");
    passwordLabel.setBounds(100, 110, 200, 25);
    panel.add(passwordLabel);

    JPasswordField passwordField = new JPasswordField();
    passwordField.setBounds(100, 135, 200, 25);
    panel.add(passwordField);

    JLabel tagLabel = new JLabel("Tag* @");
    tagLabel.setBounds(100, 160, 200, 25);
    panel.add(tagLabel);

    JTextField tagField = new JTextField();
    tagField.setBounds(100, 185, 200, 25);
    panel.add(tagField);

    JLabel avatarLabel = new JLabel("Avatar");
    avatarLabel.setBounds(100, 210, 200, 25);
    panel.add(avatarLabel);

    JTextField avatarField = new JTextField();
    avatarField.setBounds(100, 235, 200, 25);
    panel.add(avatarField);

    JLabel obligatoireLabel = new JLabel("* Obligatoire");
    obligatoireLabel.setBounds(100, 260, 200, 25);
    panel.add(obligatoireLabel);

    JButton createButton = new JButton("Create");
    createButton.setBounds(100, 285, 90, 25);
    UserController userController = new UserController(mEntityManager, (Database) mDatabase);

    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (nameField.getText().isEmpty() || passwordField.getPassword().toString().isEmpty()
                || tagField.getText().isEmpty()) {
          JOptionPane.showMessageDialog(TwitubCreateUser.this.mFrame, "Name, password and tag are required");
        } else if (userController.createUser(nameField.getText(), passwordField.getText(),
                tagField.getText(), avatarField.getText())) {
          JOptionPane.showMessageDialog(TwitubCreateUser.this.mFrame, "User created");
          panel.setVisible(false);
          twitubMainView.loadTweetFilter("");
        } else {
          JOptionPane.showMessageDialog(TwitubCreateUser.this.mFrame, "User tag already exist");
        }
      }
    });
    createButton.setBackground(new java.awt.Color(157, 153, 255));
    createButton.setForeground(new java.awt.Color(255, 255, 255));
    panel.add(createButton);

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBounds(210, 285, 90, 25);
    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        panel.setVisible(false);
        twitubMainView.loadTweetFilter("");
      }
    });
    cancelButton.setBackground(new java.awt.Color(157, 153, 255));
    cancelButton.setForeground(new java.awt.Color(255, 255, 255));
    panel.add(cancelButton);

    return panel;
  }
}
