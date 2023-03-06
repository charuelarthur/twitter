package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

  public JPanel show(List<Twit> twits) {
    return initJPanel(twits);
  }

  private JPanel initJPanel(List<Twit> twits)  {
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    for (Twit twit : twits) {
      panel.add(this.addTwitPanel(twit), c);
      c.gridy++;
    }
    return panel;
  }

  private JPanel addTwitPanel(Twit twit) {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.black));

    //AVATAR
    JLabel userLabel = new JLabel(twit.getTwiter().getName() + " @" + twit.getTwiter().getUserTag()+" - ");
    //date label timestamp to date
    Date date = new Date(twit.getEmissionDate());
    Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    JLabel dateLabel = new JLabel(format.format(date));
    JLabel textLabel = new JLabel(twit.getText());

    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    panel.add(userLabel, c);
    c.gridy = 0;
    c.gridx = 2;
    panel.add(dateLabel, c);
    c.gridx = 0;
    c.gridy = 1;
    panel.add(textLabel, c);

    return panel;
  }
}
