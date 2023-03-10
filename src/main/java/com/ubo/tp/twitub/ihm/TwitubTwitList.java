package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    if(twits.size() == 0) {
      panel.add(new JLabel("No twit found"), c);
    } else {
      for (Twit twit : twits) {
        panel.add(this.addTwitPanel(twit), c);
        c.gridy++;
      }
    }

    return panel;
  }

  private JPanel addTwitPanel(Twit twit) {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.black));
    panel.setAutoscrolls(true);
    //AVATAR
    JLabel userLabel = new JLabel(twit.getTwiter().getName() + " @" + twit.getTwiter().getUserTag()+" - ");
    //text label in bold
    userLabel.setFont(new Font("Serif", Font.BOLD, 12));

    //color blue
    userLabel.setForeground(Color.BLUE);

    userLabel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println(twit.getTwiter());
        twitubMainView.frame.remove(twitubMainView.panelProfile);
        twitubMainView.loadProfile(twit.getTwiter());
      }
    });

    //date label timestamp to date
    Date date = new Date(twit.getEmissionDate());
    Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
    JLabel dateLabel = new JLabel(format.format(date));
    JLabel textLabel = new JLabel(twit.getText());

    panel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
   //set labels to the left
   // c.fill = GridBagConstraints.LINE_START;

    c.gridx = 0;
    c.gridy = 0;
    //userLabel.setVerticalAlignment(SwingConstants.LEADING);
    panel.add(userLabel, c);
    c.gridx = 1;
    c.gridy = 0;
    panel.add(dateLabel, c);
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 3;
    panel.add(textLabel, c);

    return panel;
  }
}
