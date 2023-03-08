package main.java.com.ubo.tp.twitub.controller;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;

import javax.jws.Oneway;
import java.util.HashSet;
import java.util.Set;

public class TweetController {

  private EntityManager mEntityManager;
  private Database mDatabase;

  public TweetController(Database mDatabase, EntityManager mEntityManager) {
    this.mDatabase = mDatabase;
    this.mEntityManager = mEntityManager;
  }

  public boolean addTweet(String text) {
    if (text.length() > 0) {
      Twit twit = new Twit(mEntityManager.getCurrentUser(), text);
      //mDatabase.addTwit(twit);
      this.mEntityManager.sendTwit(twit);
      return true;
    }
    return false;
  }


  public Set<Twit> filterTweet(String filter) {
    Set<Twit> twits;
    if (filter.startsWith("@")) {
      User user = this.mDatabase.getUser(filter.substring(1));
      twits = this.mDatabase.getTwitsWithUserTag(filter.substring(1));
      twits.addAll(this.mDatabase.getUserTwits(user));
    } else if (filter.startsWith("#")) {
      twits = this.mDatabase.getTwitsWithTag(filter.substring(1));
    } else if (filter.length() == 0){
      twits = this.mDatabase.getTwits();
    } else {
      User user = this.mDatabase.getUser(filter.substring(0));
      twits = this.mDatabase.getTwitsWithUserTag(filter.substring(0));
      twits.addAll(this.mDatabase.getUserTwits(user));
      twits.addAll(this.mDatabase.getTwitsWithTag(filter.substring(0)));
    }

    return twits;
  }
}
