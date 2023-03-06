package main.java.com.ubo.tp.twitub.controller;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.Database;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.Twit;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;

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
}
