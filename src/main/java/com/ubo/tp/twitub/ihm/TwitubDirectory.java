package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;

import javax.swing.*;

public class TwitubDirectory {

  protected JFileChooser fileChooser;

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  public TwitubDirectory(IDatabase database, EntityManager entityManager) {
    this.mDatabase = database;
    this.fileChooser = new JFileChooser();
    this.mEntityManager = entityManager;
  }

  public String init() {
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    int returnVal = fileChooser.showOpenDialog(this.fileChooser);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    } else {
      System.exit(0);
    }
    return null;
  }

}
