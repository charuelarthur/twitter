package main.java.com.ubo.tp.twitub.controller;

import main.java.com.ubo.tp.twitub.core.EntityManager;
import main.java.com.ubo.tp.twitub.datamodel.IDatabase;
import main.java.com.ubo.tp.twitub.datamodel.User;
import main.java.com.ubo.tp.twitub.ihm.TwitubMainView;

public class LoginController {
  private IDatabase mDatabase;
  private EntityManager mEntityManager;
  public LoginController(IDatabase mDatabase, EntityManager entityManager, TwitubMainView twitubMainView) {
    this.mDatabase = mDatabase;
    this.mEntityManager = entityManager;
  }

  public boolean login(String tag, String password) {
    for (User user : mDatabase.getUsers()) {
      if (user.getUserTag().equals(tag) && user.getUserPassword().equals(password)) {
        mEntityManager.setCurrentUser(user);
        return true;
      }
    }
    return false;
  }
}
