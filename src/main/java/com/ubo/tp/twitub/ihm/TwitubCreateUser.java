package main.java.com.ubo.tp.twitub.ihm;

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
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;

    JLabel nameLabel = new JLabel("Name*");
    JTextField nameField = new JTextField();
    nameField.setColumns(20);
    JLabel passwordLabel = new JLabel("Password*");
    JPasswordField passwordField = new JPasswordField();
    passwordField.setColumns(20);
    JLabel tagLabel = new JLabel("Tag* @");
    JTextField tagField = new JTextField();
    tagField.setColumns(20);
    JLabel avatarLabel = new JLabel("Avatar");
    JTextField avatarField = new JTextField();
    JLabel obligatoireLabel = new JLabel("* Obligatoire");
    avatarField.setColumns(20);
    JButton createButton = new JButton("Create");
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

    panel.add(nameLabel, c);
    c.gridx = 1;
    panel.add(nameField, c);
    c.gridx = 0;
    c.gridy = 1;
    panel.add(passwordLabel, c);
    c.gridx = 1;
    panel.add(passwordField, c);
    c.gridx = 0;
    c.gridy = 2;
    panel.add(tagLabel, c);
    c.gridx = 1;
    panel.add(tagField, c);
    c.gridx = 0;
    c.gridy = 3;
    panel.add(avatarLabel, c);
    c.gridx = 1;
    panel.add(avatarField, c);
    c.gridx = 0;
    c.gridy = 4;
    panel.add(obligatoireLabel, c);
    c.gridx = 1;
    panel.add(createButton, c);

    return panel;
  }


  private void init() {
    UserController userController = new UserController(this.mEntityManager, (Database) this.mDatabase);

    JLabel nameLabel = new JLabel("Name*");
    JTextField nameField = new JTextField();
    nameField.setColumns(20);
    JLabel passwordLabel = new JLabel("Password*");
    JPasswordField passwordField = new JPasswordField();
    passwordField.setColumns(20);
    JLabel tagLabel = new JLabel("Tag* @");
    JTextField tagField = new JTextField();
    tagField.setColumns(20);
    JLabel avatarLabel = new JLabel("Avatar");
    JTextField avatarField = new JTextField();
    JLabel obligatoireLabel = new JLabel("* Obligatoire");
    avatarField.setColumns(20);
    JButton createButton = new JButton("Create");
    createButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent arg0) {
        if (nameField.getText().isEmpty() || passwordField.getPassword().toString().isEmpty()
                || tagField.getText().isEmpty()) {
          JOptionPane.showMessageDialog(TwitubCreateUser.this.mFrame, "Name, password and tag are required");
        } else if (userController.createUser(nameField.getText(), passwordField.getPassword().toString(),
                tagField.getText(), avatarField.getText())) {
          JOptionPane.showMessageDialog(TwitubCreateUser.this.mFrame, "User created");
          TwitubCreateUser.this.mFrame.dispose();
        } else {
          JOptionPane.showMessageDialog(TwitubCreateUser.this.mFrame, "User tag already exist");
        }
      }
    });

    this.mFrame.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(nameField, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(tagLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(tagField, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(avatarLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(avatarField, new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(obligatoireLabel, new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
    this.mFrame.add(createButton, new GridBagConstraints(0, 5, 2, 1, 1, 1, GridBagConstraints.CENTER,
            GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
  }


}
