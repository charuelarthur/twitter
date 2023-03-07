package main.java.com.ubo.tp.twitub.ihm;

import main.java.com.ubo.tp.twitub.controller.TweetController;
import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TwitubProfile {

    protected IDatabase mDatabase;

    protected EntityManager mEntityManager;

    TwitubMainView twitubMainView;

    protected User user;

    public TwitubProfile(IDatabase mDatabase, EntityManager mEntityManager, TwitubMainView twitubMainView, User user) {
        this.mDatabase = mDatabase;
        this.mEntityManager = mEntityManager;
        this.twitubMainView = twitubMainView;
        this.user = user;
    }

    public JPanel show() {
        return this.initJPanel();
    }

    private JPanel initJPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.ipady = gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 2;
        gc.weighty = 2;

        gc.gridx = 0;
        gc.gridy = 0;

        JLabel label = new JLabel("Profile");
        label.setPreferredSize(new Dimension(380,10));
        gc.insets = new Insets(5, 20, 5, 5);
        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(label, gc);

        JLabel nameLabel = new JLabel(user.getName());
        nameLabel.setPreferredSize(new Dimension(350,10));
        gc.insets = new Insets(5, 40, 5, 5);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.weightx = 1;
        panel.add(nameLabel, gc);

        String userTag = "@" + user.getUserTag();

        JLabel tagLabel = new JLabel(userTag);
        tagLabel.setPreferredSize(new Dimension(350,10));
        gc.insets = new Insets(5, 40 , 5, 5);
        gc.gridx = 0;
        gc.gridy = 2;
        panel.add(tagLabel, gc);

        TweetController tweetController = new TweetController((Database) mDatabase, mEntityManager);


        for (Twit twit : tweetController.filterTweet(userTag)) {
            gc.gridy++;
            gc.insets = new Insets(5, 20 , 5, 20);
            panel.add(this.addTwitPanel(twit), gc);

        }

        return panel;
    }

    private JPanel addTwitPanel(Twit twit) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(360,20));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        //AVATAR
        JLabel userLabel = new JLabel(twit.getTwiter().getName() + " @" + twit.getTwiter().getUserTag()+" - ");
        //text label in bold
        userLabel.setFont(new Font("Serif", Font.BOLD, 12));
        //color blue
        userLabel.setForeground(Color.BLUE);
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
