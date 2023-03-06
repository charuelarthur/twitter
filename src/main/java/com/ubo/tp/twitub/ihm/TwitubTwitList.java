package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class TwitubTwitList {

  protected IDatabase mDatabase;

  protected EntityManager mEntityManager;

  TwitubMainView twitubMainView;

  public TwitubTwitList(IDatabase database, EntityManager entityManager, TwitubMainView twitubMainView) {
    this.mDatabase = database;
    this.mEntityManager = entityManager;
    this.twitubMainView = twitubMainView;
  }

  public JPanel show(Set<Twit> twits) {
    return initJPanel(twits);
  }

  private JPanel initJPanel(Set<Twit> twits)  {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    for (Twit twit : twits) {
      panel.add(new JLabel(twit.getTwiter().getName() + " : " + twit.getText()), c);
      c.gridy++;
    }
    return panel;
  }

  private JPanel addTwitPanel(Twit twit) {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.black));

    //AVATAR
    JLabel userLabel = new JLabel(twit.getTwiter().getName());
    JLabel userTag = new JLabel(twit.getTwiter().getUserTag());
    JLabel dateLabel = new JLabel(String.valueOf(twit.getEmissionDate()));
    JLabel textLabel = new JLabel(twit.getText());

    panel.add(userLabel);
    panel.add(dateLabel);
    panel.add(textLabel);



    return panel;
  }
}
